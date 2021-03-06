package org.example.consumer.controller;

import org.example.consumer.service.CurrencyDynamicsService;
import org.example.consumer.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * Controller which handle requests about currencies
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Controller
@PropertySource("classpath:application.properties")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private CurrencyDynamicsService currencyDynamicsService;

    @Value("${rest.url.rub_dynamics}")
    private String rubDynamics;
    @Value("${rest.url.usd_dynamics}")
    private String usdDynamics;
    @Value("${rest.url.eur_dynamics}")
    private String eurDynamics;

    /**
     * Get view "ind"
     *
     * @return ind.html
     */
    @GetMapping("/")
    public String main() {
        return "ind";
    }

    /**
     * Get view "main"
     *
     * @param date To find currency on this date
     * @param startDate To find currency dynamics from this date
     * @param endDate To find currency dynamics to this date
     * @return main.html
     */
    @GetMapping("/table")
    public String getCurrency(
            Model model,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        if(startDate == null || endDate == null) {
            endDate = LocalDate.now().toString();
            startDate = LocalDate.now().minusDays(30).toString();
        }

        model.addAttribute("endDate",endDate);
        model.addAttribute("startDate", startDate);
        model.addAttribute("today", LocalDate.now().toString());
        model.addAttribute("currencies", currencyService.getAllCurrencies(date));
        model.addAttribute("RubDynamics", currencyDynamicsService
                .getDynamicsFromStartDateToEndDate(rubDynamics, startDate, endDate));
        model.addAttribute("UsdDynamics", currencyDynamicsService
                .getDynamicsFromStartDateToEndDate(usdDynamics, startDate, endDate));
        model.addAttribute("EurDynamics", currencyDynamicsService
                .getDynamicsFromStartDateToEndDate(eurDynamics, startDate, endDate));
        model.addAttribute("datesArray", currencyDynamicsService
                .getDatesBetweenStartDateAndEndDate(startDate, endDate));

        return "main";
    }
}
