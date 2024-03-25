package org.ashfaq.dev.Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Locks : Alternative way of achieving synchronization how we used synchronization keyword to synchronize the threads 
//here locks can be used to achieve the same thing  especially Reentrant locks and this is more convenient compared to
//use synchronization blocks as the locks has some extra features
public class LockIntro {

	// lock is an interface and Reentrant lock is its implementation
	// here we have to define fairness as a boolean parameter and if its true then
	// the longest waiting thread will
	// also can acquire a lock on the task , and this will not cause any thread
	// starvation scenarios

	// if fairness is defined as false , then there is no access order

	// good practice : We have to use the try-catch-finally block
	// when doing the critical section and call unlock() in finally block

	private static int counter = 0;

	// we are calling lock in main methods which is static , so we made the lock as
	// static this can be non static as well

	private static Lock lock = new ReentrantLock();
//	 ReentrantLock lock = new ReentrantLock();
//	ReentrantLock() constructor basically  has 2 constructor one ReentrantLock(); and ReentrantLock(boolean fairness);
	// By default, a ReentrantLock is unfair, meaning there is no guarantee about
	// the order in which waiting threads
//	will acquire the lock. Fairness must be explicitly requested by passing true to the ReentrantLock(boolean fairness) constructor if you want the lock to abide by 
//	the first-come-first-served principle, 
//	which can lead to reduced throughput but ensures no thread starvation.

	public static void incrementer() {
		lock.lock();

		try {
			for (int i = 0; i < 10000; i++) {
				counter++;

			}

		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}

	}

	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(() -> {
			incrementer();
		});

		Thread t2 = new Thread(() -> {
			incrementer();
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println(counter);

		// advantages of Lock compared to synchronized blocks

		// we can call the lock.unclock in a completely different independent method

//		Using ReentrantLock for thread synchronization in Java offers several advantages over traditional synchronized methods
//		and blocks, especially in terms of flexibility, functionality, and performance. Here's a closer look at these advantages:
//
//		Lock Fairness: One of the key advantages of ReentrantLock is the ability to specify the fairness policy. By 
//setting the fairness parameter to true, you ensure that the longest-waiting thread will get the lock next. This prevents thread starvation but may impact performance due to the overhead of managing the threads' queue. Fairness is not something that can be directly controlled with synchronized blocks/methods.
//
//		Try Locking: ReentrantLock provides a tryLock() method, which attempts to acquire the lock without blocking 
//indefinitely. This method can return immediately with a boolean value indicating whether the lock was acquired. It allows a program to check lock status and respond accordingly, avoiding potential deadlock situations. This capability is not available with the synchronized keyword.
//
//		Interruptible Lock Acquisition: The lockInterruptibly() method allows a thread to attempt to acquire the lock 
//unless it's interrupted, providing a way to break out of waiting for a lock in response to an interrupt. This adds flexibility in handling interruptions and cancellations, which isn't possible with synchronized blocks/methods.
//
//
//		Condition Support: ReentrantLock supports Condition instances, which can be used to create fine-grained lock
// policies and complex patterns for thread communication that are not possible with the intrinsic condition queues provided by synchronized blocks/methods. Conditions allow threads to wait for specific conditions to be true before proceeding, enabling more sophisticated thread coordination.
//
//		Lock Reentrancy: Like the implicit locks used by synchronized blocks/methods, ReentrantLock is reentrant, 
//meaning that a thread can acquire a lock it already owns multiple times without causing a deadlock. While this feature is not unique to ReentrantLock, it's an important aspect that ensures consistency in behavior with synchronized locking.
//
//		Scalability and Performance: For certain patterns of code and data access, ReentrantLock can offer better 
//scalability and performance. The ability to control fairness and the availability of try and timed lock attempts allow for optimization that can lead to more efficient concurrency control and better application performance.
//
//		Lock Unwinding: With ReentrantLock, you have the flexibility to lock and unlock over different scopes and 
//to handle unlocking in a finally block. This ensures that locks are always released properly, even in the case of an exception, reducing the risk of deadlocks. This level of control is more granular and flexible compared to synchronized blocks/methods.
//
//		While ReentrantLock offers these advantages, it's important to note that it comes with its own set of 
//complexities. The need to manually manage lock acquisition and release (including in finally blocks to ensure locks are always released) introduces a risk of errors, such as forgetting to release a lock, which can lead to deadlocks or other concurrency issues. Therefore, while ReentrantLock is a powerful tool for certain use cases, it should be used when there is a clear benefit over the simpler synchronized approach.
//		
//		
	}
}
