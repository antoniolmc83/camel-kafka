package com.example.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class GroupItemStrategy implements AggregationStrategy{

	@Override
	 public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Message newIn = newExchange.getIn();
        TestBean newBody = newIn.getBody(TestBean.class);
        List<TestBean> list = null;
        
        if (oldExchange == null) {
                list = new ArrayList<>();
                list.add(newBody);
                newIn.setBody(list);
                return newExchange;
        } else {
                Message in = oldExchange.getIn();
                list = in.getBody(ArrayList.class);
                
                list.add(newBody);
                
                int count=0;
                for(TestBean t: list ) {
                	
//                	if( list.get(i) instanceof TestBean ) {
                		
//                		TestBean t = list.get(i); 
                		
                		if(t.getId().equals(newBody.getId())){
                    		count++;
                    		if(count == 2) {
                            	oldExchange.setProperty("BREAK", "TRUE");
                            	break;
                            }
                    		
                    	}	
//                	}
                	
                	
                }
                
                
                
                
                return oldExchange;
        }
	}

}
