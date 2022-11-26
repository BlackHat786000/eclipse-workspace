package com.learn.practice;

import java.util.function.Function;

public class FunctionInterface {

	public static void main(String[] args) {
		
		Function<Integer,Integer> f1 = i -> i + i;
		Function<Integer,Integer> f2 = i -> i + 1000;
		
		System.out.println(f1.andThen(f2).apply(5));

	}

}
