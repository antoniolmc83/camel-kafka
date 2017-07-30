package com.example;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bean.LogTable;

@Component
public class BeanProcessor implements Processor{
	
	@Autowired
	private ProducerTemplate producerTemplate;

	@Override
	public void process(Exchange exchange) throws Exception {
		LogTable logTable = new LogTable();
		String time = String.valueOf(new Date().getTime());
		logTable.setPackageId(time);
		logTable.setTransactionId(time);
		logTable.setComponentId(time.substring(0, 9));
		logTable.setMessageBody(new Date().toString());
		
		producerTemplate.sendBody("direct:mysql", logTable);
		
		exchange.getOut().setBody(logTable);
	
	}

}
