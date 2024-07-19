package org.ashfaq.dev;

public class MainThread {
	public static void maiMainThreadn(String[] args) {

		String name = Thread.currentThread().getName();
		System.out.println(name);

		Thread t1 = new Thread(new Thread1());
		t1.setName("download images");
		Thread t2 = new Thread(new Thread2());
		t2.setName("Process images");

		t1.start();
		t2.start();

		System.out.println(t1.getName());
		System.out.println(t2.getName());

	}
}
