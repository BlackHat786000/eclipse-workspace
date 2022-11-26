package com.learn.practice;

public interface PracticeInterface1 {
	
	default void show() {
		System.out.println("This is default implementation for PracticeInterface-1");
	}
	
	static void print() {
		System.out.println("This is static utility method");
	}

}
