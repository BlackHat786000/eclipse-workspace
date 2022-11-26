package com.multithreading.inter_thread_communication;

public class MainCal {

	public static void main(String[] args) throws InterruptedException {

		Calculate p = new Calculate();
		p.start();
//		Thread.sleep(1000);
		synchronized (p) {
			p.wait(1000);
			System.out.println(p.total);
		}
		
		for(int i=0; i<50; i++) {
			System.out.println("==== MAIN THREAD ====");
			}

	}

}
