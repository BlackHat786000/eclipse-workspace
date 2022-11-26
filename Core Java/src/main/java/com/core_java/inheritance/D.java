package com.core_java.inheritance;

public class D extends C {

	public static void main(String[] args) {

		D d = new D();
		d.display();
		d.print();

	}

	@Override
	void print() {
		System.out.println("MY NAME IS UDIT YADAV. I AM JAVA DEVELOPER");

	}

}
