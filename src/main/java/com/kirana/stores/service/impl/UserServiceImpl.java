package com.kirana.stores.service.impl;
import com.kirana.stores.model.User;
import com.kirana.stores.service.UserService;
import com.kirana.stores.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        super();
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }
}
