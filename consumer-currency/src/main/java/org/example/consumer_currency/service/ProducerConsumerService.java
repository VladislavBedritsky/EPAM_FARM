package org.example.consumer_currency.service;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerConsumerService {

    @Autowired
    private CamelContext producerCamelContext;
    @Autowired
    private CamelContext consumerCamelContext;

    public void produceJsonArrayOfCurrenciesToActiveMQ(String...objectToActiveMQ) {
        try {
            producerCamelContext.start();

            ProducerTemplate producerTemplate = producerCamelContext.createProducerTemplate();
            producerTemplate.sendBody("direct:start", objectToActiveMQ);

            producerCamelContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] consumeJsonArrayOfCurrenciesFromActiveMQ() {
        String[] currencies = new String[0];
        try {
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
