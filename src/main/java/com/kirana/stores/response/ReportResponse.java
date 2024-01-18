package com.kirana.stores.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportResponse {
    private float totalCredit;

    private float totalDebit;

    private float netTransaction;
}
