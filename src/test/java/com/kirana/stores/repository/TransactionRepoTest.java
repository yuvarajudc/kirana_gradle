package com.kirana.stores.repository;

import com.kirana.stores.model.Transaction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDateTime;

@DataMongoTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TransactionRepoTest {
    @Autowired
    private TransactionRepo transactionRepo;

    @Test
    public void test_saveTransaction(){
        Transaction transaction = new Transaction("120", "credit", 25, LocalDateTime.of(2022, 6, 15, 14, 30), "INR", 33);

        Transaction savedTransaction = transactionRepo.save(transaction);

        Assertions.assertThat(savedTransaction).isNotNull();
        //Assertions.assertThat(savedTransaction.getId()).isNotEmpty();
    }
}
