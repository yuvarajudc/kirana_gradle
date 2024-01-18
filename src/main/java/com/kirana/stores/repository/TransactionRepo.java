package com.kirana.stores.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.kirana.stores.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public  interface TransactionRepo extends MongoRepository<Transaction, String> {
    List<Transaction> findByTimeStampBetween(LocalDateTime start, LocalDateTime end);
}

