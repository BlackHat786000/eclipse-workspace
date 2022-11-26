package com.collection.hashmap;

import java.util.HashMap;

public class HashMapUserObjectKey {

	public static void main(String[] args) {
		
		Employee e1 = new Employee("UDIT",1);
		Employee e2 = new Employee("UDIT",1);
		Employee e3 = new Employee("UDIT",1);
		Employee e4 = new Employee("UDIT",1);
		
		HashMap<Employee, String> map = new HashMap<Employee, String>();
		map.put(e1, "VALUE 1");
		map.put(e2, "VALUE 2");
		map.put(e3, "VALUE 3");
		map.put(e4, "VALUE 4");
		
//		e4.setEid(5);
//		
//		System.out.println(map.get(e4));
		
//		for(Map.Entry<Employee, String> m : map.entrySet()) {
//			System.out.println(m.getKey() + "===" + m.getValue());
//		}
		
		map.forEach((k,v) -> {
			System.out.println("Key : "+k+"_____"+"value : "+v);
		});
		
		// to get keys
		for(Employee k : map.keySet()) {
			System.out.println(k);
		}
		
		// to get values
		for(String v : map.values()) {
			System.out.println(v);
		}

	}

}
