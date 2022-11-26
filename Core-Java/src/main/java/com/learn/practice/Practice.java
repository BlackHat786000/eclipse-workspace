package com.learn.practice;

public class Practice implements PracticeInterface1,PracticeInterface2 {
	
	public void show() {
		PracticeInterface2.super.show();
	}

	public static void main(String[] args) {
		
		PracticeInterface1 practiceInterface = new Practice();
		practiceInterface.show();
		
		PracticeInterface1.print();

	}

}
