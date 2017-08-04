package com.example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
		
		List<String> list  = Arrays.asList("10","1","2","3","4","5","6","7","8","9"
				,"20","11","12","13","14","15","16","17","18","19",
				"30","21","22","23","24","25","26","27","28","29",
				"40","31","32","33","34","35","36","37","38","39",
				"50","41","42","43","44","45","46","47","48","49",
				"60","51","52","53","54","55","56","57","58","59",
				"70","61","62","63","64","65","66","67","68","69",
				"80","71","72","73","74","75","76","77","78","79",
				"90","81","82","83","84","85","86","87","88","89",
		       "0","91","92","93","94","95","96","97","98","99"
		       );

		list.parallelStream().forEach(l -> {
			LogTable item = buildLogTable(l);
			
			producerTemplate.sendBody("direct:mysql", item);
				
		});
		
//		exchange.getOut().setBody(logTable);
	
	}
	
	
	private LogTable buildLogTable(String l){
		LogTable logTable = new LogTable();
		String time = String.valueOf(new Date().getTime());
		logTable.setPackageId(l + time);
		logTable.setTransactionId(time);
		logTable.setComponentId(l);
		logTable.setMessageBody(new Date().toString());
		
		return logTable;
	}

}
