package org.ashfaq.dev;

//Note as you , extend a class Thread the class itself becomes a Thread 
class Runner3 extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i <= 10; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Runner 3 : " + i);
		}

	}
//	public void execute() {
//
//		for (int i = 0; i <= 10; i++) {
//
//			System.out.println("Runner 1 : " + i);
//		}
//	}

}

class Runner4 extends Thread {
//	public void execute() {
//		for (int i = 0; i <= 10; i++) {
//
//			System.out.println("Runner 2 : " + i);
//		}
//
//	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i <= 10; i++) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Runner 4 : " + i);
		}

	}

}

public class Sequential_Processing2_Thread {
	public static void main(String[] args) {

		// starting the thread and since we are creating a thread we can call like this
//		Thread t1 = new Runner3();
//		Thread t2 = new Runner4();
//		t1.start();
//		t2.start();
//		

//		Providing Logic directly as a anonymous or Lambda

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				for (int i = 0; i <= 10; i++) {

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("Runner 6 : " + i);
				}

			}
		});
		Thread t2 = new Thread(() -> {
			for (int i = 0; i <= 10; i++) {

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Runner 7 : " + i);
			}
		});
		t1.start();
		t2.start();

	}
}
