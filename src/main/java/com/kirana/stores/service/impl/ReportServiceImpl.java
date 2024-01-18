package com.kirana.stores.service.impl;

import com.kirana.stores.model.Transaction;
import com.kirana.stores.service.ReportService;
import java.time.LocalDateTime;
import com.kirana.stores.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kirana.stores.response.ReportResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private TransactionRepo transactionRepo;

    public ReportResponse getStatistics(List<Transaction> transactions){
        float totalCredit = 0, totalDebit = 0, netTransaction = 0;
        for (Transaction transaction : transactions) {
            if (Objects.equals(transaction.getType(), "credit")){
                totalCredit += transaction.getAmountToDollar();
            }
            else {
                totalDebit += transaction.getAmountToDollar();
            }
            netTransaction = totalCredit-totalDebit;
        }

        //List<Float> stats = new ArrayList<Float>(Arrays.asList(totalCredit, totalDebit, netTransaction));
        return new ReportResponse(totalCredit, totalDebit, netTransaction);
    }
    @Override
    public ReportResponse getWeeklyTransaction() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneWeekAgo = now.minusDays(7);
        System.out.println("inside week");
        List<Transaction> result = transactionRepo.findByTimeStampBetween(oneWeekAgo,now);
//        float totalCredit = 0, totalDebit = 0, netTransaction = 0;
//
//
//        System.out.println("total credit: " + totalCredit + "total debit: " + totalDebit + "net transaction: " + netTransaction);
        return getStatistics(result);

    }

    @Override
    public ReportResponse getMonthlyTransaction() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minusMonths(1);
        List<Transaction> result = transactionRepo.findByTimeStampBetween(oneMonthAgo, now);
        return getStatistics(result);
    }

    @Override
    public ReportResponse getYearlyTransaction() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneYearAgo = now.minusYears(1);
        List<Transaction> result = transactionRepo.findByTimeStampBetween(oneYearAgo, now);
        return getStatistics(result);
    }

}
