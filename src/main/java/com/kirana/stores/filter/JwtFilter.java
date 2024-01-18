package com.kirana.stores.filter;

import com.kirana.stores.configuration.RateLimitConfig;
import com.kirana.stores.service.impl.JwtServiceImpl;
import com.kirana.stores.service.impl.UserDetailsInfo;
import com.kirana.stores.service.impl.UserInfoUserDetailServiceImpl;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;



@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserInfoUserDetailServiceImpl userDetailService;
    @Autowired
    private RateLimitConfig rateLimitConfig;
    @Autowired
    private JwtServiceImpl jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;
        System.out.println("Token : " + authHeader.substring(7));
        System.out.println(authHeader != null);
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            System.out.println("If passed");
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }
        System.out.println("Username Extracted" + username);
        if (username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails = userDetailService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                Bucket bucket = rateLimitConfig.resolveBucket("SER");
                if (bucket.tryConsume(1)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.equals("/authenticate/token");
    }
}
