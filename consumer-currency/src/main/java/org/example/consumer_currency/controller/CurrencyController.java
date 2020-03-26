package org.example.consumer_currency.controller;

import org.example.consumer_currency.service.CurrencyDynamicsService;
import org.example.consumer_currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@PropertySource("classpath:application.properties")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private CurrencyDynamicsService currencyDynamicsService;

    @Value("${rest.url.rub_dynamics}")
    private String RUB_DYNAMICS;
    @Value("${rest.url.usd_dynamics}")
    private String USD_DYNAMICS;
    @Value("${rest.url.eur_dynamics}")
    private String EUR_DYNAMICS;


    @GetMapping("/currency")
    public String getMain(
            Model model,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) throws Exception {

        if(startDate == null || endDate == null) {
            endDate = LocalDate.now().toString();
            startDate = LocalDate.now().minusDays(30).toString();
        }

        model.addAttribute("endDate",endDate);
        model.addAttribute("startDate", startDate);
        model.addAttribute("today", LocalDate.now().toString());
        model.addAttribute("currencies", currencyService.getAllCurrencies(date));
        model.addAttribute("RubDynamics", currencyDynamicsService
                .getDynamicsFromStartDateToEndDate(RUB_DYNAMICS,startDate, endDate));
        model.addAttribute("UsdDynamics", currencyDynamicsService
                .getDynamicsFromStartDateToEndDate(USD_DYNAMICS,startDate, endDate));
        model.addAttribute("EurDynamics", currencyDynamicsService
                .getDynamicsFromStartDateToEndDate(EUR_DYNAMICS,startDate, endDate));
        model.addAttribute("datesArray", currencyDynamicsService.getDatesBetweenStartDateAndEndDate(startDate,endDate));

        return "main";
    }
}
