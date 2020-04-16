package org.example.consumer.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CurrencyDynamicsService {

    private static Logger LOGGER = LogManager.getLogger(CurrencyDynamicsService.class);

    @Autowired
    private CurrencyService currencyService;

    public Double[] getDynamicsFromStartDateToEndDate(String typeOfCurrencyUrl, String startDate, String endDate) {
        String url=typeOfCurrencyUrl+startDate+"&endDate="+endDate;
        ArrayList<Double> list = new ArrayList<>();
        String json = null;

        try {
            json = currencyService.getJsonFromRestUrl(url);
        } catch (IOException e) {
            LOGGER.error(e);
        }

        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.getJSONObject(i).getDouble("Cur_OfficialRate"));
        }

        Double[] result = new Double[list.size()];
        list.toArray(result);

        return result;
    }

    public List<String> getDatesBetweenStartDateAndEndDate(String startDate, String endDate) {

        LocalDate st = LocalDate.parse(startDate);
        LocalDate ed = LocalDate.parse(endDate);

        List<String> dates = Stream.iterate(st, amount -> amount.plusDays(1))
                .limit(ChronoUnit.DAYS.between(st,ed))
                .map(LocalDate::toString)
                .collect(Collectors.toList());

        return dates;
    }

}
