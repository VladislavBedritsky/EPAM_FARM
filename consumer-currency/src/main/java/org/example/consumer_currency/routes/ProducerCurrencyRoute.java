package org.example.consumer_currency.routes;

import org.apache.camel.builder.RouteBuilder;

public class ProducerCurrencyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:start")
                .to("activemq:queue:first_queue?jmsMessageType=Object");
    }
}
