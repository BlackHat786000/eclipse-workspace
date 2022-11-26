package com.multithreading.inter_thread_communication;

public class MovieBookApp {

	public static void main(String[] args) {

		TotalEarning t = new TotalEarning();

		t.start();

//		System.out.println("TOTAL EARNING = " + t.total_earning);
		
		synchronized(t) {
			try {
				t.wait();
				System.out.println("TOTAL EARNING = " + t.total_earning);
			} catch (InterruptedException e) {
				
			}
		}

	}

}
