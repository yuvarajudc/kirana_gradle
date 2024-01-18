package com.kirana.stores.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
@Data
@AllArgsConstructor
public class ExchangeRateResponse {
    private boolean success;
    private String terms;
    private String privacy;
    private long timestamp;
    private String date;
    private String base;

    @JsonProperty("rates")
    private Map<String, Double> rates;


}
