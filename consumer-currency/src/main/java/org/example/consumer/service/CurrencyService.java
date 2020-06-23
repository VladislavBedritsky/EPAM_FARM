package org.example.consumer.service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.consumer.model.Currency;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Currency service
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Service
@PropertySource("classpath:application.properties")
public class CurrencyService {

    private static Logger LOGGER = LogManager.getLogger(CurrencyService.class);
    @Autowired
    private CurrencyProducerConsumer currencyProducerConsumer;
    @Value("${rest.url.rub}")
    private String urlRUB;
    @Value("${rest.url.usd}")
    private String urlUSD;
    @Value("${rest.url.eur}")
    private String urlEUR;

    /**
     * Get JSON in String type from REST with specified URL.
     *
     * @param url URL
     * @return JSON in String type
     */
    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
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

    /**
     * Get Array with JSON of currencies in String type with specified date.
     *
     * @param date Date from UI
     * @return array of currencies
     */
    public String[] getArrayForActiveMQ (String date) throws IOException {
        String[] arrayForActiveMQ;

        String currencyRUB = getJsonFromRestUrl(urlRUB+date);
        String currencyUSD = getJsonFromRestUrl(urlUSD+date);
        String currencyEUR = getJsonFromRestUrl(urlEUR+date);

        arrayForActiveMQ = new String[] {
                    currencyRUB,
                    currencyUSD,
                    currencyEUR };

        return arrayForActiveMQ;
    }

    /**
     * Get JSONObject with specified JSON of currency in String type.
     *
     * @param json JSON in String type
     * @return JSONObject with currency
     */
    public JSONObject convertStringToJsonObject(String json) {
        return new JSONObject(json);
    }

    /**
     * Get Currency from specified JSONObject.
     *
     * @param jsonObject JSONObject with currency
     * @return JSONObject with currency
     */
    public Currency setCurrency(JSONObject jsonObject) {
        Currency currency = new Currency();
        currency.setAbbreviation(jsonObject.getString("Cur_Abbreviation"));
        currency.setDate(jsonObject.getString("Date"));
        currency.setName(jsonObject.getString("Cur_Name"));
        currency.setOfficialRate(jsonObject.getDouble("Cur_OfficialRate"));
        currency.setScale(jsonObject.getInt("Cur_Scale"));
        return currency;
    }

    /**
     * Get list of currencies in String type with specified date
     * from ActiveMQ.
     *
     * @param date Specified date
     * @return list of currencies in String type
     */
    public List<String> getListFromActiveMQ(String date) {
        try {
            currencyProducerConsumer.produceJsonArrayOfCurrenciesToActiveMQ(
                    getArrayForActiveMQ(
                            date
            ));
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return new ArrayList<>(Arrays.asList(
                currencyProducerConsumer.consumeJsonArrayOfCurrenciesFromActiveMQ()
        ));
    }

    /**
     * Get list of currencies with specified date.
     *
     * @param date Specified date
     * @return list of currencies
     */
    public List<Currency> getAllCurrencies(String date) {
        List<Currency> currencyList = new ArrayList<>();

        for (String temp : getListFromActiveMQ(date)) {
            currencyList.add(
                    setCurrency(
                            convertStringToJsonObject(temp)
                    )
            );
        }

        return currencyList;
    }


}
