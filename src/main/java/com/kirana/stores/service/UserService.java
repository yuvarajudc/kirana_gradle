package com.kirana.stores.service;

import com.kirana.stores.model.User;
public interface UserService {
    User addUser(User user);

    void deleteUser(String id);
}
