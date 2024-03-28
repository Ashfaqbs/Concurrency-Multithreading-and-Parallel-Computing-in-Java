package org.ashfaq.dev.concepts;

//The volatile keyword ensures visibility of changes to the `terminated` flag across threads.

// Without volatile, there's no guarantee that changes made by one thread to `terminated`
// would be visible to other threads. This might lead to a situation where the loop in the run()
// method continues indefinitely, even after `terminated` is set to true on another thread.

class Worker implements Runnable {

	// by adding volatile we are making sure this variable is available in main
	// thread so all the threads can refer it
	// if not added it can happen like the variable can or cannot be available in
	// main memory
	private volatile boolean terminated;

//so now we have added volatile keyword all the threads will be aware of the changes to the `terminated` variable 
	// so the thread can stop once we set to true else if volatile not added the
	// thread may not stop
	@Override
	public void run() {

		while (!terminated) {
			System.out.println("Worker Thread is Running");

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public boolean isTerminated() {
		return terminated;
	}

	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}

}

public class Volatile {

	public static void main(String[] args) {

		Worker worker = new Worker();

		Thread t1 = new Thread(worker);

		t1.start();

		try {
			Thread.sleep(3000);// Main thread sleeps, simulating some operation
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		worker.setTerminated(true);// Properly communicate the stop signal to the worker thread

		System.out.println("Worker thread is terminated");

	}

}
