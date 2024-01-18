package com.kirana.stores.controller;

import com.kirana.stores.response.ExchangeRateResponse;
import com.kirana.stores.model.Transaction;
import com.kirana.stores.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.kirana.stores.service.ExternalApiService;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class TransactionController {
    private final TransactionService myTranService;
    private final ExternalApiService apiService;
    public TransactionController(TransactionService myTranService, ExternalApiService apiService) {
        super();
        this.myTranService = myTranService;
        this.apiService = apiService;
    }

    @PostMapping
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction tr) {

        return new ResponseEntity<Transaction>(myTranService.saveTransaction(tr), HttpStatus.CREATED);
    }

//    @GetMapping("/yuvraj")
//    public ResponseEntity<String> getYuvraj(){
//        return ResponseEntity.ok("Yuvraj here!");
//    }
}
