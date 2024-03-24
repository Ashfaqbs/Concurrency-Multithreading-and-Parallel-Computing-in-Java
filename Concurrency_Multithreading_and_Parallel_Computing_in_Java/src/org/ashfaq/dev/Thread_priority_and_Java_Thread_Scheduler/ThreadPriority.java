package org.ashfaq.dev.Thread_priority_and_Java_Thread_Scheduler;

class WorkerThread implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 10; i++) {
			System.out.println("Worker thread with max priority : " + i);
		}
	}
}

public class ThreadPriority {

	public static void main(String[] args) {

		
////**		Higher priority threads are executed before lower priority threads but it depends
//		on the underlying OS (thread starvation is avoided) which means if a given thread 
//		higher priority is going to be executed 1st then it may happen that a given thread 
//		with low priority is not going to be executed at all and off course JVM and thread 
//		scheduler wants to avoid this situation so this is why despite the fact the thread 
//		has a high priority the thread with
//		lower priority will be executed as well by the CPU for a little time slice or time 
//**//		
		//thread with max priority 10
		Thread t = new Thread(new WorkerThread());
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();

		//Main thread with default priority 5 
		System.out.println("this is main thread ");

		
		
		
	}

}
