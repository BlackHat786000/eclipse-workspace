package com.java.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class movie implements Runnable {

	public static void main(String[] args) {
		
//		movie mov = new movie();
//		Thread m = new Thread(mov);
//		m.start();
//		System.out.println("justice league");
		
//		System.out.println(String.format("My %1s is %1s", "Name"));
//		System.out.println(String.format("My %s is %s","Name"));
//		System.out.println(String.format("My %1$s is %1$s","Name"));
//		System.out.println(String.format("My %1$s is %2$s","Name"));
//		System.out.println(String.format("My %1$s is %2$s", "Name", "UDIT YADAV"));
		
//		StringBuilder str = new StringBuilder("TECHNOCURATORS");
//		str = str.replace(6, 12, "STA");
//		System.out.println(str);
//		
//		StringBuilder str2 = new StringBuilder("UDITYADAV");
//		str2.replace(3, 8, "YO");
//		System.out.println(str2);
		
//		byte a = 10;
//		Integer c;
//		c = new Integer(a).BYTES;
//		System.out.println(c);
//		System.out.println(new Long(10).BYTES);
//		System.out.println(new Float(10).BYTES);
//		System.out.println(c.equals(new Long(10).BYTES));
		
//		List> list = new ArrayList<>();
//		list.add(new HashMap<>());
//		Map map = (Map) list.get(0);
//		map.put("key", "value");
		
//		Stream stream = Stream.of(10,20,"30");
//		boolean out = stream.allMatch(in -> in instanceof Number);
//		System.out.println(out);
		
//		List<? super Integer> list = new ArrayList<Number>();
//		int i=7;
//		list.add(i);
//		list.add(++i);
//		Number num = list.get(0);
//		System.out.println(num);
		
//		long a = 88;
//		Integer b = new Integer((int) a);
//		Integer c = b+0;
//		Integer d = c;
//		System.out.println(b==c);
//		System.out.println(c==d);
		
//		String reg = "([^\\W])[0-9]+[\\d]*[\\s]*\\1";
//		String input = "$*454--$";
//		System.out.println(input.matches(reg));
		
//		String reg = "[\\w]+ [\\w]+ [\\w]+";
//		String input = "1_A 2_B C_3";
//		System.out.println(input.matches(reg));

		try {
			int i = 5/0;
		} catch(Exception e) {
			System.out.println("==== Exception caught ====");
			e.printStackTrace();
//			System.out.println(e.getMessage());
		}

	}
	
	public void run() {
		System.out.println("avengers");
	}
	

}
