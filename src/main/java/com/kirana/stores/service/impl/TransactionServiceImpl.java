package com.kirana.stores.service.impl;

import com.kirana.stores.model.Transaction;
import com.kirana.stores.repository.TransactionRepo;
import com.kirana.stores.response.ExchangeRateResponse;
import com.kirana.stores.service.TransactionService;
import org.springframework.stereotype.Service;
import com.kirana.stores.service.ExternalApiService;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo myTrans;
    private final ExternalApiService externalApiService;
    public TransactionServiceImpl(TransactionRepo myTrans, ExternalApiService externalApiService) {
        super();
        this.myTrans = myTrans;
        this.externalApiService = externalApiService;
    }

    @Override
    public Transaction saveTransaction(Transaction tr) {
        String id = tr.getId();
        String type = tr.getType();
        float amt = tr.getAmount();
        String currency = tr.getCurrency();
        LocalDateTime timeStamp = LocalDateTime.now();

        ExchangeRateResponse response = externalApiService.fetchDataFromExternalApi();
        Map<String, Double> rates = response.getRates();
        Double exchangeValue = rates.get(currency);
        float currencyToDollar = (float) (amt/exchangeValue);
        System.out.println("currency converted to dollar value is " + currencyToDollar);
        Transaction request = new Transaction(id, type, amt, timeStamp, currency, currencyToDollar);
        return myTrans.save(request);
    }
}
