package org.example.consumer_currency.service;

import org.example.consumer_currency.model.Currency;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private ProducerConsumerService producerConsumerService;

    private String urlRub = "http://www.nbrb.by/api/exrates/rates/rub?parammode=2";
    private String urlUsd = "http://www.nbrb.by/api/exrates/rates/usd?parammode=2";
    private String urlEur = "http://www.nbrb.by/api/exrates/rates/eur?parammode=2";


    public String getJsonFromRestUrl(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()
                )
        );

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();

        return response.toString();
    }

    public String[] getArrayForActiveMQ () throws Exception {
        String[] arrayForActiveMQ = new String[0];

        String currencyRUB = getJsonFromRestUrl(urlRub);
        String currencyUSD = getJsonFromRestUrl(urlUsd);
        String currencyEUR = getJsonFromRestUrl(urlEur);

        arrayForActiveMQ = new String[] {
                    currencyRUB,
                    currencyUSD,
                    currencyEUR };

        return arrayForActiveMQ;
    }

    public JSONObject convertStringToJsonObject(String json) {
        return new JSONObject(json);
    }

    public Currency setCurrency(JSONObject jsonObject) {
        Currency currency = new Currency();
        currency.setAbbreviation(jsonObject.getString("Cur_Abbreviation"));
        currency.setDate(jsonObject.getString("Date"));
        currency.setName(jsonObject.getString("Cur_Name"));
        currency.setOfficialRate(jsonObject.getDouble("Cur_OfficialRate"));
        currency.setScale(jsonObject.getInt("Cur_Scale"));
        return currency;
    }

    public List<String> getListFromActiveMQ() {
        try {
            producerConsumerService.produceJsonArrayOfCurrenciesToActiveMQ(getArrayForActiveMQ());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>(Arrays.asList(
                producerConsumerService.consumeJsonArrayOfCurrenciesFromActiveMQ()
        ));
    }

    public List<Currency> getAllCurrencies() {
        List<Currency> currencyList = new ArrayList<>();

        for (String temp : getListFromActiveMQ()) {
            currencyList.add(
                    setCurrency(
                            convertStringToJsonObject(temp)
                    )
            );
        }

        return currencyList;
    }

}
