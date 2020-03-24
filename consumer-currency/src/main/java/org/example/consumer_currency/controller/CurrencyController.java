package org.example.consumer_currency.controller;

import org.example.consumer_currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/")
    public String getMain(Model model) throws Exception {
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        return "main";
    }
}
