package org.ashfaq.dev.waitAndNotify;

public class ProducerConsumerproblem {
//whether its possible to communicate between the 2 threads and release the intrinsic lock and communicate between the 2 threads and the answer is 
//	yes , and we have to use wait and notify
	private void producer() {
		// TODO Auto-generated method stub

		synchronized (this) {

			System.out.println("starting the producer");

			System.out.println("Sendind data to consumer");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				wait();
				// important as this a object(single object and threads are dependent as well
				// )intrinsic lock, so lock will be released only when the thread completes its
				// task and the other
//				thread can acquire lock but once we call the wait the 1st thread which executed all the above logic before wait() will release the lock and other 
//				thread will starts executing its by acquiring the lock and once the other threads which started executing only if calls it calls the notify()
//				the 1st thread will run again so its like 1st T started executing its logic then went to wait condition as wait() was called the other 
				// respective thread started executing its logic then called notify() so the 2st
				// thread proceeded
//
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Data is sent is completed");
			System.out.println("Closing the producer");

		}

	}

	private void consumer() {
		synchronized (this) {

			System.out.println("starting the consumer");

			System.out.println("Receiving data from producer consumer");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			notify();

			System.out.println("Data process is completed");
			System.out.println("Closing the consumer");

		}

	}

	public static void main(String[] args) {

		ProducerConsumerproblem consumerproblem = new ProducerConsumerproblem();

		Thread t1 = new Thread(() -> {
			consumerproblem.producer();
		});

		Thread t2 = new Thread(() -> {
			consumerproblem.consumer();
		});

		t1.start();
		t2.start();

	}

}
