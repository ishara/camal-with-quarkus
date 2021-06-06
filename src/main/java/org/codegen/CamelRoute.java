/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codegen;

import org.apache.camel.ErrorHandlerFactory;
import org.apache.camel.builder.ErrorHandlerBuilder;
import org.apache.camel.builder.ErrorHandlerBuilderRef;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.builder.endpoint.StaticEndpointBuilders;
import org.apache.camel.builder.endpoint.dsl.FileEndpointBuilderFactory;

import javax.enterprise.context.ApplicationScoped;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class CamelRoute extends EndpointRouteBuilder {

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .to(log("LOGGER-CR"));

        from(platformHttp("/camel/hello"))
                .setBody().simple("Camel runs on ${hostname} - return and log")
//                .to(log("hi").showExchangePattern(false).showBodyType(false))
                .to(log("LOGGER-CR"))
                .to(StaticEndpointBuilders.file("foo").fileName("foo.txt"))
        .to("file:bar?doneFileName=done");


//        from(StaticEndpointBuilders.twitterSearch("twitterSearch")
//                .accessToken("q").accessTokenSecret("q").consumerKey("q").consumerSecret("q"))
        from("direct:DistributeOrderDSL")
        .to(log("LOGGER-CR"))
                .to("stream:out");

        ;
    }
}
