package com.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRouter extends RouteBuilder {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void configure() throws Exception {
		
		
		from("timer:hello?period={{timer.period}}")
//        .transform(method("myBean", "saySomething"))
//        .setHeader(Exchange.HTTP_METHOD, constant("GET"))
//        .to("http://localhost:8081/testobj")
		.process("proccesorBean")
        .log("${body}")
		.end();
		 
		from("direct:start").process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
            	logger.info("Preparing message ...1");
                exchange.getIn().setBody("Test Message from Camel Kafka Component Final",String.class);
                exchange.getIn().setHeader(KafkaConstants.PARTITION_KEY, 0);
                exchange.getIn().setHeader(KafkaConstants.KEY, "1");
                logger.info("Preparing message ...2");
            }
        }).to("kafka:localhost:9092?topic=test");
	}

}
