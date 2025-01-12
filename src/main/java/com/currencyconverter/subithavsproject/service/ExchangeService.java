package com.currencyconverter.subithavsproject.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public Map<String, Double> getExchangeRates(String baseCurrency) {
        String url = API_URL + baseCurrency;
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            return (Map<String, Double>) response.getBody().get("rates");
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch exchange rates");
        }
    }

    public double convertCurrency(String from, String to, double amount) {
        Map<String, Double> rates = getExchangeRates(from);
        if (rates == null || !rates.containsKey(to)) {
            throw new IllegalArgumentException("Invalid currency code");
        }
        return rates.get(to) * amount;
    }

}
