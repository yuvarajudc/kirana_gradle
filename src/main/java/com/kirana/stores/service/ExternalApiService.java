package com.kirana.stores.service;

import com.kirana.stores.response.ExchangeRateResponse;

public interface ExternalApiService {
    public ExchangeRateResponse fetchDataFromExternalApi();
}
