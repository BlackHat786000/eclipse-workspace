package com.core_java.inheritance;

public class B extends A {

	B() {
//		super(7);
		System.out.println("In default constructor of class B");
	}

	B(int i) {
		super(7);
		System.out.println("In parameterized constructor of class B");
	}

//	@Override
//	public void show() {
//		System.out.println("In show() of class B");
//	}

	public static void main(String[] args) {
		B b1 = new B();
		B b2 = new B(5);
		b2.show();

	}
}