package com.kirana.stores.service.impl;

import com.kirana.stores.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.kirana.stores.repository.UserRepo;

import java.util.Optional;

@Component
public class UserInfoUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

//    public UserInfoUserDetailServiceImpl(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(username);
        return user.map(UserDetailsInfo::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }
}
