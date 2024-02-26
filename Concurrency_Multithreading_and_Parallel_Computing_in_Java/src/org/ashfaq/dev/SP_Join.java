package org.ashfaq.dev;

import java.util.Iterator;

class Thread1 implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 11; i++) {
			System.out.println("Runner 1 : " + i);

		}

	}
}

class Thread2 implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 11; i++) {
			System.out.println("Runner 2 : " + i);

		}

	}
}

public class SP_Join {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Starting the program");

		// here we want to add multithreading , so we need to make two threads one for
		// downloading the images and one for processing the images
		// now just creating a threads for each and we ran the code , we will see the
		// main thread
		// which has print start and end will execute and this thread 1 and 2 will run
		// on thier own/
		// but what if i want to not to print end but to wait only to execute the thread
		// 1 or 2 or both
		// are completed then execute print end then we need to use join();
//	downloadImages();
//	ProcessImages();

		Thread t1 = new Thread(new Thread1());

		Thread t2 = new Thread(new Thread2());

		t1.start();
		t2.start();

		t2.join();
		t1.join();

		System.out.println("Ending the Program");

	}
}
