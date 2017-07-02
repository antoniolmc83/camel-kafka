package com.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProccesorBean implements Processor{

	private final Logger logger = LoggerFactory.getLogger(ProccesorBean.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		long startTime = System.currentTimeMillis();
		
		String fooResourceUrl = "http://localhost:8081/testobj";
		RestTemplate restTemplate = new RestTemplate();
//		int times = 10;
		ResponseEntity<String> response = null;
//		Map<String, String> parameters = new HashMap<>();
		System.out.println(System.getProperty("http.maxConnections"));
//		System.setProperty("http.maxConnections", "3");
		
		List<String> list  = Arrays.asList("10","1","2","3","4","5","6","7","8","9"
				,"20","11","12","13","14","15","16","17","18","19",
				"30","21","22","23","24","25","26","27","28","29",
				"40","31","32","33","34","35","36","37","38","39",
				"50","41","42","43","44","45","46","47","48","49",
				"60","51","52","53","54","55","56","57","58","59",
				"70","61","62","63","64","65","66","67","68","69",
				"80","71","72","73","74","75","76","77","78","79",
				"90","81","82","83","84","85","86","87","88","89",
		       "100","91","92","93","94","95","96","97","98","99"
		       );
		
		final ConcurrentSkipListMap<Long, String> finalResponses= new ConcurrentSkipListMap<>();
		
		list.parallelStream().forEach( l -> {
			ResponseEntity<String> response1 = restTemplate.getForEntity(fooResourceUrl + "/" + l , String.class);
			String body = response1.getBody();
//			String body = "Test";
			finalResponses.put(Long.valueOf(l), body);
			logger.info(response1.toString());
		} );
		
		
		
//		for(int i=0; i<times; i++){
//			
//			response = restTemplate.getForEntity(fooResourceUrl + "/" + list.get(i) , String.class);
//			System.out.println(response);
//		}
		System.out.println("-----> Finisshhhhhhhh");
		Iterator<Long> key = finalResponses.keySet().iterator();
		while(key.hasNext()){
			Long k = key.next();
			logger.info(k + " - " + finalResponses.get(k));
		}
		System.out.println(System.getProperty("http.maxConnections"));
		logger.info("Total time: " + (System.currentTimeMillis() - startTime));
		exchange.getOut().setBody(response);
		
	}
	
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
	    int timeout = 5000;
	    

	    
	    PoolingHttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager();
	    poolingConnManager.setDefaultMaxPerRoute(100);
	    poolingConnManager.setMaxTotal(100);
	    
//	    HttpClient httpClient = HttpClientBuilder.create()
//        .setConnectionManager(poolingConnManager)
//        .build();	    
	    
	    CloseableHttpClient client
	    = HttpClients.custom().setConnectionManager(poolingConnManager)
	    .build();
	    
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
	      = new HttpComponentsClientHttpRequestFactory(client);
	    clientHttpRequestFactory.setConnectTimeout(timeout);
	    
	    return clientHttpRequestFactory;
	}

}
