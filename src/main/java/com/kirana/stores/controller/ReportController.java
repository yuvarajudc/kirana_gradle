package com.kirana.stores.controller;

import com.kirana.stores.model.Transaction;
import com.kirana.stores.response.ReportResponse;
import com.kirana.stores.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")

public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/weekly")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ReportResponse> getWeeklyReport() {
        System.out.println("inside weekly report");
        return new ResponseEntity<ReportResponse>(reportService.getWeeklyTransaction(), HttpStatus.CREATED);
    }

    @GetMapping("/monthly")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ReportResponse> getMonthlyReport() {
        return new ResponseEntity<ReportResponse>(reportService.getMonthlyTransaction(), HttpStatus.CREATED);
    }

    @GetMapping("/yearly")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ReportResponse> getYearlyReport() {
        return new ResponseEntity<ReportResponse>(reportService.getYearlyTransaction(), HttpStatus.CREATED);
    }
}
