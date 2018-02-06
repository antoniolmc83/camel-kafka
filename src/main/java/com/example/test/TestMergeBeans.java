package com.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TestMergeBeans {


	
	public static void main(String []args) {
		TestMergeBeans t = new TestMergeBeans();
		MergerBean mb = new MergerBean();
		mb.mergeBeans( t.createBeans() );
	}
	

	
	

	
	
	public List<TestBean> createBeans(){
		
		List<TestBean> list = new ArrayList<>();
		
		
		int maxCount = 100;
		int maxId = 80;
		int minId = 1;
		Random r = new Random();
		
		TestBean bean = null;
		Map<Integer, Integer> mapCount = new HashMap<>();
		
		System.out.println("CreateBeans  ");
		
		do { 
			bean = new TestBean();
			Integer id = r.nextInt( maxId - minId + 1 ) + minId;
			int count = mapCount.containsKey(id)?(mapCount.get(id)+1):1;
//			mapCount.merge(id, 1, (value, newvalue)->value + 1);
			
			if( count > 2 ) continue;
			
			mapCount.put(id, count);
			
			bean.setId( String.valueOf(( id )) );
			bean.setHistoryType(  id%2==0 ? MergerBean.ADD_STATUS : MergerBean.DELETE_STATUS );
			bean.setAttribute("attribute " + id + " " + bean.getHistoryType());
			bean.setAttribute1("attribute1");
			bean.setAttribute2("attribute2");
			list.add( bean);
		}while( list.size() < maxCount );
		
	
		List<TestBean> resp = list.stream().sorted( (o1, o2)-> Integer.valueOf(o1.getId()).compareTo(Integer.valueOf(o2.getId())) ).collect(Collectors.toList()); 
		

		
		System.out.println("FinishBeans  " + list.size());
		
		return resp;
	}
	
}
