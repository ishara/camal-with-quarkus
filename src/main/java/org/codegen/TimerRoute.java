package org.codegen;

import org.apache.camel.builder.RouteBuilder;

import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class TimerRoute extends RouteBuilder {

    @ConfigProperty(name = "timer.period", defaultValue = "1000")
    String period;

    @Override
    public void configure() throws Exception {
        fromF("timer:foo?period=%s", period)
                .setBody(exchange -> "Incremented the counter: " + period)
                .to("log:cdi-example?showExchangePattern=false&showBodyType=false");
    }
}