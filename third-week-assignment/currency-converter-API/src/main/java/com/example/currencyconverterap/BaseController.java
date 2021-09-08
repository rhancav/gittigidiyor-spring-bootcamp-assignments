package com.example.currencyconverterap;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.NumberFormat;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class BaseController {
    private static final double TRY_USD = 8.0;
    private static final Locale TR_LOCALE = new Locale("tr","TR");
    private static final Locale US_LOCALE = new Locale("en","US");

    @GetMapping("/convert-usd")
    public ResponseEntity<String> convertUSD(@RequestParam double amount){
        double convertedAmount = amount/TRY_USD;
        return ResponseEntity.ok(NumberFormat.getCurrencyInstance(TR_LOCALE).format(convertedAmount));
    }
    @GetMapping("/convert-try")
    public ResponseEntity<String> convertTRY(@RequestParam double amount){
        double convertedAmount = amount*TRY_USD;
        return ResponseEntity.ok(NumberFormat.getCurrencyInstance(US_LOCALE).format(convertedAmount));
    }
}
