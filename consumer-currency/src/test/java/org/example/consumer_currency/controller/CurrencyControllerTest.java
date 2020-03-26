package org.example.consumer_currency.controller;

import org.example.consumer_currency.service.CurrencyService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CurrencyControllerTest {

    private CurrencyService currencyService = new CurrencyService();

    @Test
    public void getMain() throws IOException {

        String url="http://www.nbrb.by/API/ExRates/Rates/Dynamics/298?startDate=2019-12-12&endDate=2019-12-15";
//        URL obj = new URL(url);
//        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        String q = currencyService.getJsonFromRestUrl(url);
        System.out.println(q);

        JSONArray arr = new JSONArray(q);
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.getJSONObject(i).getDouble("Cur_OfficialRate"));
        }


    }
}