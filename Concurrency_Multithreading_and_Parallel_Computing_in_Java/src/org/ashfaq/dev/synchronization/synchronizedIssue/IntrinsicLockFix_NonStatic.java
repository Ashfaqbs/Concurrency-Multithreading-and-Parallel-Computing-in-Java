package org.ashfaq.dev.synchronization.synchronizedIssue;

public class IntrinsicLockFix_NonStatic {

	public static int counter1 = 0;
	public static int counter2 = 0;

	private final Object lock1 = new Object();
	private final Object lock2 = new Object();

	public void incrementer1() {

		synchronized (lock1) {
			counter1++;
		}
	}

	public void incrementer2() {
		synchronized (lock2) {
			counter2++;
		}
	}

// lock1 will lock the 1st method and lock2 will lock the 2nd method so there
// the two independent threads have independent locks
//and in intrinsic lock (class or object ) we had one lock because of this the other thread it not dependent also had to wait , here we have 2 locks , 
//	so for thread1 if we have any other thread which is dependent 
//then it will wait and thread2 as its independent of thread1 it will run with its own lock and it will only wait if any dependent thread is running and 
//has acquired the lock and both the independent threads will run independently

	public void process() throws InterruptedException {

		Thread t1 = new Thread(() -> {
			for (int i = 0; i <= 100; i++)
				incrementer1();
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i <= 100; i++)
				incrementer2();
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("Counter 1 : " + counter1);

		System.out.println("Counter 2 : " + counter2);

	}

////	
//	Separate Locks for Independent Operations: By creating two separate lock objects, lock1 and lock2,
//	you've correctly identified a method to allow incrementer1() and incrementer2() to execute concurrently without 
//	interfering with each other, as long as they're not accessed by more than one thread simultaneously.
//	This is a classic and effective solution for increasing concurrency in your application, reducing the contention
//among threads that are performing unrelated tasks.
//
//	Correctness of Your Synchronization Strategy: Your statement about having two locks
//	allowing the threads to run independently is accurate. When incrementer1() is executed, 
//	it acquires lock1, and when incrementer2() is executed, it acquires lock2. This means that one
//	thread can be incrementing counter1 while another is incrementing counter2, without waiting for
//	the other to release a single, shared lock. This approach effectively solves the issue you identified in 
//	the previous examples, where threads unnecessarily blocked each other due to a single intrinsic lock associated
//	with the class when methods were marked as synchronized.
//	
//	
//	Understanding Intrinsic Locks: Your explanation about intrinsic locks (monitor locks) is correct. 
//	By default, every object in Java has an intrinsic lock associated with it. When a method is declared as 
//	synchronized, it uses the intrinsic lock of the object (for instance methods) or the class (for static methods) 
//	to ensure mutual exclusion.
//	Your approach of using separate objects as locks (lock1 and lock2) to guard access to counter1 
//	and counter2 respectively, ensures that operations on these counters do not block each other unnecessarily.
////	
	public static void main(String[] args) throws InterruptedException {
		new IntrinsicLockFix_NonStatic().process();
	}
}
