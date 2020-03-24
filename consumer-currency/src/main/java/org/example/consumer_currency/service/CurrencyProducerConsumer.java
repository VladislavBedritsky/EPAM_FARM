package org.example.consumer_currency.service;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.example.consumer_currency.routes.ConsumerCurrencyRoute;
import org.example.consumer_currency.routes.ProducerCurrencyRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyProducerConsumer {

    @Autowired
    private ActiveMQConnectionFactory connectionFactory;

    @Autowired
    private ProducerCurrencyRoute producerCurrencyRoute;

    @Autowired
    private ConsumerCurrencyRoute consumerCurrencyRoute;

    public void produceJsonArrayOfCurrenciesToActiveMQ(String...objectToActiveMQ) {
        try {
            CamelContext camelContext = new DefaultCamelContext();
            camelContext.addComponent("jms", JmsComponent.jmsComponent(connectionFactory));

            camelContext.addRoutes(producerCurrencyRoute);
            camelContext.start();

            ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
            producerTemplate.sendBody("direct:start", objectToActiveMQ);
            camelContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] consumeJsonArrayOfCurrenciesFromActiveMQ() {
        String[] currencies = new String[0];
        try {
            CamelContext camelContext = new DefaultCamelContext();
            camelContext.addComponent("jms", JmsComponent.jmsComponent(connectionFactory));

            camelContext.addRoutes(consumerCurrencyRoute);
            camelContext.start();

            ConsumerTemplate consumerTemplate = camelContext.createConsumerTemplate();
            currencies = consumerTemplate.receiveBody("seda:end", String[].class);
            camelContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return currencies;
    }

}
