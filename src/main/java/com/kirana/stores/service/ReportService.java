package com.kirana.stores.service;
import com.kirana.stores.model.Transaction;
import com.kirana.stores.response.ReportResponse;

import java.util.List;

public interface ReportService {
    ReportResponse getWeeklyTransaction();

    ReportResponse getMonthlyTransaction();

    ReportResponse getYearlyTransaction();
}
