package org.example.consumer.service;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * class CurrencyProducerConsumer
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Service
public class CurrencyProducerConsumer {

    private static Logger LOGGER = LogManager.getLogger(CurrencyProducerConsumer.class);

    @Autowired
    private CamelContext producerCamelContext;

    @Autowired
    private CamelContext consumerCamelContext;

    /**
     * Produce array with JSON of currencies to ActiveMQ.
     *
     * @param objectToActiveMQ Array of JSON in String type
     */
    public void produceJsonArrayOfCurrenciesToActiveMQ(String...objectToActiveMQ) {
        try {
            producerCamelContext.start();

            ProducerTemplate producerTemplate = producerCamelContext.createProducerTemplate();
            producerTemplate.sendBody("direct:start", objectToActiveMQ);
            producerCamelContext.stop();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Consume array with JSON of currencies from ActiveMQ.
     *
     * @return Array of JSON in String type
     */
    public String[] consumeJsonArrayOfCurrenciesFromActiveMQ() {
        String[] currencies = new String[0];
        try {
            consumerCamelContext.start();

            ConsumerTemplate consumerTemplate = consumerCamelContext.createConsumerTemplate();
            currencies = consumerTemplate.receiveBody("seda:end", String[].class);
            consumerCamelContext.stop();
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return currencies;
    }

}
