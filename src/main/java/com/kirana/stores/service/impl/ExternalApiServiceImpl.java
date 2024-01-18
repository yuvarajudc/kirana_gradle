package com.kirana.stores.service.impl;

import com.kirana.stores.response.ExchangeRateResponse;
import com.kirana.stores.service.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.kirana.stores.configuration.AppConfig;

@EnableCaching
@Service
public class ExternalApiServiceImpl implements ExternalApiService{
    @Autowired
    private final RestTemplate restTemplate;

    public ExternalApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(cacheManager = "cacheManager",cacheNames = "fetchDataFromExternalApi")
    public ExchangeRateResponse fetchDataFromExternalApi(){
        return restTemplate.getForObject("https://api.fxratesapi.com/latest", ExchangeRateResponse.class);
    }
}
