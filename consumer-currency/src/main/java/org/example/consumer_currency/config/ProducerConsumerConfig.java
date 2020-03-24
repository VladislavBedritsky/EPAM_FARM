package org.example.consumer_currency.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.example.consumer_currency.routes.ConsumerCurrencyRoute;
import org.example.consumer_currency.routes.ProducerCurrencyRoute;
import org.example.consumer_currency.service.CurrencyProducerConsumer;
import org.example.consumer_currency.service.CurrencyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConsumerConfig {

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:8161");
        activeMQConnectionFactory.setUserName("admin");
        activeMQConnectionFactory.setPassword("admin");
        return activeMQConnectionFactory;
    }

    @Bean
    public ProducerCurrencyRoute producerCurrencyRoute() {
        return new ProducerCurrencyRoute();
    }

    @Bean
    public ConsumerCurrencyRoute consumerCurrencyRoute() {
        return new ConsumerCurrencyRoute();
    }

    @Bean
    public CurrencyProducerConsumer currencyProducerConsumer() {
        return new CurrencyProducerConsumer();
    }

    @Bean
    public CurrencyService currencyService() {
        return new CurrencyService();
    }
}
