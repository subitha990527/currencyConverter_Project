package com.currencyconverter.subithavsproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.currencyconverter.subithavsproject.service.ExchangeService;

@SpringBootTest
class SubithavsprojectApplicationTests {


	@Autowired
    private ExchangeService exchangeService;

    @Test
    void testConversion() {
        // double result = exchangeService.convertCurrency("USD", "EUR", 100);
        // Assertions.assertTrue(result > 0);
		    double result = exchangeService.convertCurrency("USD", "EUR", 100);
    System.out.println("Converted amount: " + result);
    assertTrue(result > 0, "The converted amount should be greater than 0");
    }

    @Test
    void testInvalidCurrency() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            exchangeService.convertCurrency("USD", "XYZ", 100);
        });
    }

}
