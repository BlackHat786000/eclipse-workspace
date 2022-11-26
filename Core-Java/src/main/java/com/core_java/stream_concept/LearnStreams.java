package com.core_java.stream_concept;

import java.util.Arrays;

public class LearnStreams {
	public static void main(String[] args) {

//		List<Integer> list = new ArrayList<Integer>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		list.add(5);
//		list.add(6);
		
		/* List<Integer> even = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());

		for (Integer i : even) {
			System.out.print(i);
		}

		list.stream().forEach(i -> System.out.println(i));

		List<Integer> add2 = list.stream().map(i -> i + 2).collect(Collectors.toList());
		System.out.print(add2); */

//		list.stream().forEach(i -> { 
//		
//			if(i%2 == 0) { System.out.println(i); }
//		
//		});
		
		int nums[] = {100,200,55,66,77,88,99};
		
		int newarr[] = Arrays.stream(nums).map(a -> a % 10).toArray();
		
		Arrays.stream(newarr).forEach(e -> System.out.print(e+" "));

	}
}