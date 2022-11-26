package com.java.multithreading;

public class Main {

	public static void main(String[] args) throws Exception {

		Thread1 t1 = new Thread1();
		Thread thread1 = new Thread(t1);
		Thread2 t2 = new Thread2();
		Thread thread2 = new Thread(t2);

		thread1.start();
		System.out.println(Thread.currentThread().getName());
		thread1.join();
		System.out.println(Thread.currentThread().getName());
		thread2.start();
		thread2.join();

		for (int i = 0; i < 100; i++) {
			System.out.println("Main thread is executing......");
		}
	}

}
