package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.util.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/currency")
public class CurrencyConverterController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/convertUSD")
    public ResponseEntity<String> convertUSD(@RequestParam double amount) {
        String url = StringConstants.CURRENCY_CONVERTER_API + "convert-usd?amount=" + amount;
        return restTemplate.getForEntity(url, String.class);
    }

    @GetMapping("/convertTRY")
    public ResponseEntity<String> convertTRY(@RequestParam double amount) {
        String url = StringConstants.CURRENCY_CONVERTER_API + "convert-try?amount=" + amount;
        return restTemplate.getForEntity(url, String.class);
    }
}
