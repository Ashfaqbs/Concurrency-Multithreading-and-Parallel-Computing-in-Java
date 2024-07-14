package com.globalsoftwaresupport;

public class VirtualTask {

	public static void run() {
		System.out.println("Started ... " + Thread.currentThread().getName());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finish ... " + Thread.currentThread().getName());
	}
}
