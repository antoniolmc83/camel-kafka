package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergerBean {
	public static final String ADD_STATUS = "ADD";
	public static final String DELETE_STATUS = "DELETE";
	public static final String MERGE_STATUS = "MERGE";
	
	public List<TestBean> mergeBeans(List<TestBean> listBeans) {
		 
		
		Map<String, TestBean> map = new HashMap<>();
		long initTime = System.currentTimeMillis();
		System.out.println("Init time: " + initTime);
		
		for( TestBean b:listBeans ) {
			
			
			
			
			if( !map.containsKey(b.getId()) ) {
				map.put(b.getId(), b);
			}else {
				TestBean mapBean = map.get(b.getId());
				TestBean mergeBean = getAddBean(mapBean, b);
				map.put(b.getId(), mergeBean);
			}
		}
		System.out.println("Total time: " + (System.currentTimeMillis() - initTime));
		System.out.println("After merge: " + map.values().size());
		//map.values().forEach(System.out::println);
		List<TestBean> resp = new ArrayList<>(map.values()) ;
		
		return resp;
	}
	
	private TestBean getAddBean(TestBean a, TestBean b) {
		TestBean resp = null;
		
		resp = a;
		
		if(  ADD_STATUS.equals(b.getHistoryType())) {
			resp = b;
			resp.setHistoryType(MERGE_STATUS);
		}
		
		return resp;
	}
	
}
