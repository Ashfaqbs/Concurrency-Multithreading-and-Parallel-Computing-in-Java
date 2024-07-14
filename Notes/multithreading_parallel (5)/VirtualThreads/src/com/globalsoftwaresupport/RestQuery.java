package com.globalsoftwaresupport;

public class RestQuery {

	public static String run() {
		System.out.println("REST operation started...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("REST operation finished...");
		return " 23";
	}
}
