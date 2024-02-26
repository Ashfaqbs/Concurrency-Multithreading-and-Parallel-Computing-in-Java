package org.ashfaq.dev.daemonAndWorkerThreads;

class DaemonWorker implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("Daemon Thread running ..........");
		}
	}

}

class WorkerThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println("Worker Thread running ..........");
	}

}

//how to create a daemon threads and one observation of daemon thread 
// so if i have both daemon and worker threads for a main thread and as soon as i start 
// worker thread and daemon thread and if the worker thread task is completed and automatically JVM 
//will stop the daemon thread
public class DaemonAndWorkerThreads {

	public static void main(String[] args) {
		Thread t1 = new Thread(new DaemonWorker());
		t1.setDaemon(true);// by default it will be false
		Thread t2 = new Thread(new WorkerThread());
		
		
		t1.start();
		t2.start();

	}

}
