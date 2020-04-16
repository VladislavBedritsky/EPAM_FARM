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

/**
 * class CurrencyDynamicsService
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Service
public class CurrencyDynamicsService {

    private static Logger LOGGER = LogManager.getLogger(CurrencyDynamicsService.class);

    @Autowired
    private CurrencyService currencyService;

    /**
     * Get array with currency dynamics between two dates
     * with specified currency url.
     *
     * @param typeOfCurrencyUrl Currencies url
     * @param startDate Date from
     * @param endDate Date to
     * @return array with specific currency
     */
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

    /**
     * Get list of dates between two dates.
     *
     * @param startDate Date from
     * @param endDate Date to
     * @return list of dates
     */
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
