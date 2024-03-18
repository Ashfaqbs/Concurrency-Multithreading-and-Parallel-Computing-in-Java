package org.ashfaq.dev.practice;

public class SynchronizedifUsed {

	static int counter = 0;

//	public static synchronized void incrementer() { bad practice to add synchronized on method block
	public static void incrementer() {

		synchronized (SynchronizedifUsed.class) {// since the function is static so we are giving class name else this
													// keyword work if the method is
			// non static
			counter++;
		}
	}

	public static void process() throws InterruptedException {

		Thread t1 = new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				incrementer();

			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 1; i < 201; i++) {
				incrementer();

			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();
		System.out.println(" Countert Final Value is  : " + counter);

	}

	public static void main(String[] args) throws InterruptedException {

//		if the process function was not static then we can create a object and start the function
//		new SynchronizedIfNotUsed(). 
		process();

		// 5th time Counter Final Value is : 203 hence this is not deterministic , so we
		// have to use Synchronized

	}

}
