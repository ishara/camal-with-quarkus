package org.codegen;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.endpoint.StaticEndpointBuilders;

public class TimeRouterEndpointDSL extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        from(StaticEndpointBuilders.timer("foo")).delay(10000)
//                .log(LoggingLevel.INFO,"TimeRouterEndpointDSL" +System.currentTimeMillis());
    }
}
