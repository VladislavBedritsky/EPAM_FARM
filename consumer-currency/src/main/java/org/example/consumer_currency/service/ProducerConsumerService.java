package org.example.consumer_currency.service;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ProducerConsumerService {

    @Autowired
    private RouteBuilder producerCurrencyRoute;
    @Autowired
    private RouteBuilder consumerCurrencyRoute;


    public void produceJsonArrayOfCurrenciesToActiveMQ(String...objectToActiveMQ) {
        try {

            ClassPathXmlApplicationContext applicationContext =
                    new ClassPathXmlApplicationContext("classpath:*spring-camel.xml");
            CamelContext producerCamelContext = SpringCamelContext.springCamelContext(applicationContext);


            producerCamelContext.addRoutes(producerCurrencyRoute);
            producerCamelContext.start();

            ProducerTemplate producerTemplate = producerCamelContext.createProducerTemplate();
            producerTemplate.sendBody("direct:start", objectToActiveMQ);

            producerCamelContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public String[] consumeJsonArrayOfCurrenciesFromActiveMQ() {
        String[] currencies = new String[0];
        try {
            ClassPathXmlApplicationContext applicationContext =
                    new ClassPathXmlApplicationContext("classpath:*spring-camel.xml");
            CamelContext consumerCamelContext = SpringCamelContext.springCamelContext(applicationContext);

            consumerCamelContext.addRoutes(consumerCurrencyRoute);
            consumerCamelContext.start();

            ConsumerTemplate consumerTemplate = consumerCamelContext.createConsumerTemplate();
            currencies = consumerTemplate.receiveBody("seda:end", String[].class);

            consumerCamelContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return currencies;
    }


}
