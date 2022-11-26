package com.java.problems;

interface Test {
	void meth();
	default void meth(int i) {}
//	void meth(boolean b);
}
public class MyClass {

	public static void main(String[] args) {
		
		Test var = () -> {System.out.println("Hey I was defined inside a lambda");};
		
		var.meth();
	}

}
