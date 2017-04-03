package com.example.bean;

import java.util.ArrayList;
import java.util.List;

public class Person {

	private String name;
	private String lastName;
	private int age;
	private String dni;
	private Long salary;
	
	public Person() {
	}
	
	public Person(String name, String lastName, int age, String dni) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	
	public static List<Person> buildShortList(){
		List<Person> list = new ArrayList<>();
		
		list.add(new Person("Paolo", "Guerrero", 30, "42123422"));
		list.add(new Person("Christian", "Cueva", 25, "41123422"));
		list.add(new Person("Edinson", "Flores", 23, "45123422"));
		list.add(new Person("Pedro", "Galeze", 20, "42643422"));
		list.add(new Person("Alberto", "Rodriguez", 21, "44523422"));
		list.add(new Person("Culebra", "Carrillo", 32, "47533422"));
		list.add(new Person("Paolo", "Guerrero", 43, "42123422"));
		list.add(new Person("Christian", "Cueva", 23, "41123422"));
		list.add(new Person("Edinson", "Flores", 53, "45123422"));
		list.add(new Person("Pedro", "Galeze", 14, "42643422"));
		list.add(new Person("Alberto", "Rodriguez", 34, "44523422"));
		list.add(new Person("Culebra", "Carrillo", 36, "47533422"));
		list.add(new Person("Paolo", "Guerrero", 37, "42123422"));
		list.add(new Person("Christian", "Cueva", 12, "41123422"));
		list.add(new Person("Edinson", "Flores", 32, "45123422"));
		list.add(new Person("Pedro", "Galeze", 12, "42643422"));
		list.add(new Person("Alberto", "Rodriguez", 27, "44523422"));
		list.add(new Person("Culebra", "Carrillo", 34, "47533422"));		
		return list;
	}
	
	
}
