	package org.ashfaq.dev.waitAndNotify;
	
	
//	How wait() and notify() Work
//	wait(): This method is used to make a thread wait until another thread signals it to 
//	resume. When a thread calls wait(), it releases the lock it holds and enters the waiting state.
//	notify(): This method wakes up one of the threads that are waiting on the same object. It does not 
//	release the lock immediately; it releases the lock after completing the synchronized block.
//	Use Cases for wait() and notify()
//	Producer-Consumer Pattern: These methods are commonly used in scenarios where one thread produces data, and another thread consumes it. The producer 
//	thread can wait until there is data to be consumed, and the consumer thread can notify the producer when it's ready to accept more data.
//	Thread Coordination: wait() and notify() can also be used to coordinate the execution of multiple threads based on certain
//	conditions or events.
//	Resource Sharing: They can be used to ensure proper synchronization and mutual exclusion when multiple threads access shared resources.
//	In summary, wait() and notify() are fundamental methods for coordinating the behavior of multiple threads in Java, allowing 
//	them to communicate and synchronize their actions effectively.
	
	class process {
	
		public void producer() throws InterruptedException
		{
			synchronized (this) {
			System.out.println("Starting the producer");
			
			wait();
			
			System.out.println("Again back in producer");
			}
			}
		
		public void consume() throws InterruptedException
		{
			synchronized (this) {
				
			
			
			System.out.println("Starting the consumer");
			Thread.sleep(1000);
			System.out.println("Consumer is done");
			notify();}
			
		}
		
		
		
	}
	
	public class App {
		
		public static void main(String[] args) {
			
			
			
			process process = new process();
			
			Thread t1 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
				try {
					process.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					
				}
			});
			
	Thread t2 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
				try {
					process.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					
				}
			});
	
	t1.start();
	t2.start();
			
			
			
		}
	
	}
