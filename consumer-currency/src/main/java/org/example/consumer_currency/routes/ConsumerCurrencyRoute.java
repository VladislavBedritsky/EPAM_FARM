package org.example.consumer_currency.routes;

import org.apache.camel.builder.RouteBuilder;

public class ConsumerCurrencyRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:queue:first_queue")
                .to("seda:end");
    }
}
