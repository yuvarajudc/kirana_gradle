package com.kirana.stores.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String userName;
    private String password;
    private String roles;
}
