package com.example.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelismTest {

	public static void main(String args[]){
		
		List<Person> listPerson = Person.buildShortList();
		List<Person> listPerson2 = Person.buildShortList();
		
		long currTime = System.currentTimeMillis();
		Collections.sort(listPerson, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getAge()-o2.getAge();
			}
		});
		for( Person p:listPerson ){
			p.setSalary(calculateSalary(p.getAge()));
		}
		
		for( Person p:listPerson ){
			System.out.println("-> " + p.getAge() + " - " + p.getName() + " - " + p.getSalary());
		}
		System.out.println("Time taken: " + (System.currentTimeMillis() - currTime) );
		
		Comparator<Person> comparatorPerson = (Person p1, Person p2)->p1.getAge()-p2.getAge(); 
		currTime = System.currentTimeMillis();
		
		listPerson2 = listPerson2.parallelStream().sorted(comparatorPerson).collect(Collectors.toList());
		listPerson2.parallelStream().forEach(p-> {p.setSalary(calculateSalary(p.getAge()));} );
		listPerson2.stream().forEach(p -> System.out.println("-> " + p.getAge() + " - " + p.getName() + " - " + p.getSalary()));
		System.out.println("Time taken: " + (System.currentTimeMillis() - currTime) );
		
	}
	
	
	private static long calculateSalary( int age ){
		long salary = 0;
		for(long i=age; i <=age * 10000000; i++){
			salary += i;
		}
		return salary/10000000;
		
	}
	
	private void sumaTest(){
		List<Long> lista = new ArrayList<>();
		List<Long> lista2 = new ArrayList<>();
		List<Long> lista3 = new ArrayList<>();
		for(long i=0; i <=10000000; i++){
			lista.add(i);
			lista2.add(i);
			lista3.add(i);
		}
		
		long currTime = System.currentTimeMillis();
		traditionalSum(lista);
		System.out.println("Time taken: " + (System.currentTimeMillis() - currTime) );

		currTime = System.currentTimeMillis();
		java8SumSequential(lista2);
		System.out.println("Time taken: " + (System.currentTimeMillis() - currTime) );

		currTime = System.currentTimeMillis();
		java8SumParallel(lista3);
		System.out.println("Time taken: " + (System.currentTimeMillis() - currTime) );		
	}
	
	private static void traditionalSum(List<Long> list){
		long sum = 0;
		for(Long i:list){
			sum += i;
		}
		System.out.println("Suma Total Traditional: " + sum);
		
	}
	
	private static void java8SumSequential(List<Long> list){
		long suma = list.stream().mapToLong(i -> i.longValue()).sum();
		System.out.println("Suma Total Java8 Seq: " + suma);
	}
	private static void java8SumParallel(List<Long> list){
		long suma = list.parallelStream().mapToLong(i -> i.longValue()).sum();
		System.out.println("Suma Total Java8 Parallel: " + suma);
	}
	
}
