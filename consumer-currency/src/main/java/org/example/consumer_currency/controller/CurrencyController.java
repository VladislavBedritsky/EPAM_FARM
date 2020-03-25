package org.example.consumer_currency.controller;

import org.example.consumer_currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    private static final String today = LocalDate.now().toString();

    @GetMapping("/currency")
    public String getMain(
            Model model,
            @RequestParam(required = false) String date) throws Exception {
        model.addAttribute("today", today);
        model.addAttribute("currencies", currencyService.getAllCurrencies(date));
        return "main";
    }
}
