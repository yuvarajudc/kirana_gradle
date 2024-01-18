package com.kirana.stores.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@AllArgsConstructor
public class Transaction {

    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;
    private String type;
    private float amount;
    private LocalDateTime timeStamp;
    private String currency;
    private float amountToDollar;


}
