package com.kirana.stores.repository;


import com.kirana.stores.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByUserName(String username);
}
