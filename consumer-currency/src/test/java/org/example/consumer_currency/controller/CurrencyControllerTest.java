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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class CurrencyControllerTest {

    private CurrencyService currencyService = new CurrencyService();

    @Test
    public void getMain() throws IOException {



    }
}