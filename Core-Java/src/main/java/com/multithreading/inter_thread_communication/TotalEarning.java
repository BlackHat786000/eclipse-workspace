package com.multithreading.inter_thread_communication;

public class TotalEarning extends Thread {

	int total_earning = 0;

	public void run() {

		synchronized(this) {
			
		for (int i = 1; i <= 10; i++) {
			
			total_earning = total_earning + 100;

		}

		this.notify();
		
	}
		
	}

}
