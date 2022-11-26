package com.core_java.string_problems;

public class StringProblems {
	
	public static void main(String[] args) {
		String str = "4-5-2021";
		String[] strarr = str.split("-");
		System.out.println(strarr[0]+strarr[1]+strarr[2]);
	}

}
