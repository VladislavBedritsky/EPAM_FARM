package org.example.consumer_currency.service;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.example.consumer_currency.model.Currency;
import org.json.JSONObject;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.ConnectionFactory;
import javax.jms.ObjectMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JSONService {

    private String urlRub = "http://www.nbrb.by/api/exrates/rates/rub?parammode=2";
    private String urlUsd = "http://www.nbrb.by/api/exrates/rates/usd?parammode=2";
    private String urlEur = "http://www.nbrb.by/api/exrates/rates/eur?parammode=2";

    public JSONObject getJsonObjectFromRestUrl(String url) throws IOException {
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

        return new JSONObject(response.toString());
    }

    public List<String> getListWithCurrencies () throws Exception {
        List<String> currencyList = new ArrayList<>();
        try {
            String currencyRUB = getJsonObjectFromRestUrl(urlRub).toString();
            String currencyUSD = getJsonObjectFromRestUrl(urlUsd).toString();
            String currencyEUR = getJsonObjectFromRestUrl(urlEur).toString();

            currencyList.add(currencyRUB);
            currencyList.add(currencyUSD);
            currencyList.add(currencyEUR);

        } catch (IOException e) {
            e.printStackTrace();
        }

        producer(getCamelContext(), currencyList);
        return currencyList;
    }

    public CamelContext getCamelContext() {
        CamelContext camelContext = new DefaultCamelContext();

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:8161");
        camelContext.addComponent("jms", JmsComponent.jmsComponent(connectionFactory));
        return camelContext;
    }

    public void producer(CamelContext camelContext, List<String> resultToActiveMQ) throws Exception {

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("activemq:queue:first_queue");
            }
        });

        camelContext.start();

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start", resultToActiveMQ);

    }

    public List<Currency> consumer (CamelContext camelContext) throws Exception {

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("activemq:queue:first_queue")
                        .to("seda:end");
            }
        });

        camelContext.start();

        ConsumerTemplate consumerTemplate = camelContext.createConsumerTemplate();

        return consumerTemplate.receiveBody("seda:end", ArrayList.class);
    }

    public Currency getCurrency(JSONObject jsonObject) {
        Currency currency = new Currency();
        currency.setAbbreviation(jsonObject.getString("Cur_Abbreviation"));
        currency.setDate(jsonObject.getString("Date"));
        currency.setName(jsonObject.getString("Cur_Name"));
        currency.setOfficialRate(jsonObject.getDouble("Cur_OfficialRate"));
        currency.setScale(jsonObject.getInt("Cur_Scale"));
        return currency;
    }

}
