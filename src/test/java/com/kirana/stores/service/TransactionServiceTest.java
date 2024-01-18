package com.kirana.stores.service;

import com.kirana.stores.model.Transaction;
import com.kirana.stores.repository.TransactionRepo;
import com.kirana.stores.service.impl.TransactionServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private TransactionRepo transactionRepo;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    public void test_saveTransactionService(){
        Transaction transaction = new Transaction("12", "debit", 66, LocalDateTime.of(2022, 6, 15, 14, 30), "INR", 33);

        when(transactionRepo.save(Mockito.any(Transaction.class))).thenReturn(transaction);

        Transaction transaction1 = transactionService.saveTransaction(transaction);

        Assertions.assertThat(transaction1).isNotNull();
    }


}
