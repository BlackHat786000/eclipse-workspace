package com.multithreading.inter_thread_communication;

public class Calculate extends Thread  {

	int total = 0;

	public void run() {
		synchronized (this) {
//			this.notify();
			for (int i = 1; i <= 100; i++) {
				total = total + i;
			}
			this.notify();
		}
		
		for(int i=0; i<50; i++) {
		System.out.println("==== CHILD THREAD ====");
		}
	}

}
