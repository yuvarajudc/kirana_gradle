package com.kirana.stores.controller;
import com.kirana.stores.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import com.kirana.stores.service.impl.JwtServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authenticate")
public class TokenController {
    private final JwtServiceImpl jwtServiceimpl;

    private final AuthenticationManager authenticationManager;

    public TokenController(JwtServiceImpl jwtServiceimpl, AuthenticationManager authenticationManager) {
        this.jwtServiceimpl = jwtServiceimpl;
        this.authenticationManager = authenticationManager;
    }




    @PostMapping("/token")
    public String autheticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtServiceimpl.generateToken(authRequest.getUsername());
        }else {
            throw new UsernameNotFoundException("user not found");
        }

    }
}
