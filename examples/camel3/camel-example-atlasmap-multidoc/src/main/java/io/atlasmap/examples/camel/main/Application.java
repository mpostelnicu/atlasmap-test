/*
 * Copyright (C) 2017 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.atlasmap.examples.camel.main;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class Application extends RouteBuilder {

    @Override
    public void configure() throws Exception {


        from("timer:main?period=5000")
              .to("direct:order-producer")
            .to("direct:contact-producer")
                .to("direct:ip-producer")
            .to("atlasmap:atlasmap-mapping-mihai.adm")
            .to("direct:outcome-consumer");

        from("direct:ip-producer")
                .to("http://ip.jsontest.com")
                .setProperty("ip-c55dac4f-dbaf-4957-a9e4-855519da9498", body().convertToString());

        from("direct:order-producer")
            .setProperty("order-schema-75bd4163-225a-458d-8e5b-c478e0fc37de", simple("resource:classpath:data/order.json"));

        from("direct:contact-producer")
            .setProperty("contact-schema-5e97d61f-d377-4d00-885f-3178cf824f39", simple("resource:classpath:data/contact.xml"));

        from("direct:outcome-consumer")
            .log("--< Outcome: [${body}]");
    }

    public static void main(String args[]) throws Exception {
        Main camelMain = new Main();
        camelMain.configure().addRoutesBuilder(new Application());
        camelMain.run(args);
    }
}
