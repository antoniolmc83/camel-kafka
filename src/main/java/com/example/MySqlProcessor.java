package com.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bean.LogTable;
import com.example.bean.repository.LogTableRepository;

@Component
public class MySqlProcessor implements Processor{
	
	

	@Autowired
	private LogTableRepository logTableRepository;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		LogTable entity = exchange.getIn().getBody(LogTable.class);
		
		logTableRepository.save(entity);
		
		System.out.println("Grabado");
	}

}
