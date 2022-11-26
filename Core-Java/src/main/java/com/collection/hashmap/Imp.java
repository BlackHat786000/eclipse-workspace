package com.collection.hashmap;

import java.util.HashMap;
import java.util.Map.Entry;

public class Imp {

	public static void main(String[] args) {

		Integer i1 = 1;
		Integer i2 = 1;
		String s1 = "1";
		String s2 = "2";

		System.out.println(i1.equals(i2));
		System.out.println(s1.equals(s2));
		System.out.println(i1.equals(s1));

		HashMap<Object, Object> map = new HashMap<>();

		map.put("1", "String");
		map.put(1, "Integer");
		map.put("1", "String-1");
		map.put(1, "Integer-1");

		for(Entry<Object, Object> obj : map.entrySet()) {
			System.out.println("Key -> "+obj.getKey()+" , Value -> "+obj.getValue());
		}
		
//		String str = "My name is udit yadav";
//		char[] charr = str.toCharArray();
//		
//		for(int i=str.length()-1; i>=0; i--) {
//			System.out.print(charr[i]);
//		}

//		int m=5;
//		m--; //4
//		System.out.println(m); //4
//		System.out.println(m--); //4
//		System.out.println(m); //3
		
//		int i=0;
//		int m = i++; // m=0, i=1
//		
//		int j=0;
//		int n = ++j; // j=1, n=1
		
//		int c=0;
//		c = c++; //0
//		c = c++; //0
//		c = c++; //0
//		System.out.println(c);
		
//		int c=0;
//		c = ++c; //1
//		c = ++c; //2
//		c = ++c; //3
//		System.out.println(c);
		
		
		
	}

}
