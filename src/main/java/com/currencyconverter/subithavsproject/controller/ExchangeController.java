package com.currencyconverter.subithavsproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.currencyconverter.subithavsproject.service.ExchangeService;

@RestController
@RequestMapping("/api")
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/rates")
    public Map<String, Double> getRates(@RequestParam(defaultValue = "USD") String base) {
        return exchangeService.getExchangeRates(base);
    }

    @PostMapping("/convert")
    public Map<String, Object> convert(@RequestBody Map<String, Object> request) {
        String from = (String) request.get("from");
        String to = (String) request.get("to");
        double amount = (double) request.get("amount");

        double convertedAmount = exchangeService.convertCurrency(from, to, amount);
        return Map.of("from", from, "to", to, "amount", amount, "convertedAmount", convertedAmount);
    }
}
