package com.kirana.stores.controller;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.kirana.stores.model.User;
import com.kirana.stores.service.UserService;

import static java.lang.constant.ConstantDescs.NULL;

@RestController
@RequestMapping("/user")
//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.addUser(user), HttpStatus.CREATED).getBody();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
    }
}
