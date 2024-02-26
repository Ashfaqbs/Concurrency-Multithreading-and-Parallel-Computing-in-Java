package Thread_priority_and_Java_Thread_Scheduler;

public class MainThreadAndPriority {

	public static void main(String[] args) {

//		off course we can use the value within the range from 1-10 more then 10 we will run time exception
//		Thread.currentThread().setPriority(Thread.MAX_PRIORITY); //Name of the Thread : main and its Priority is : 10

//		Thread.currentThread().setPriority(Thread.MIN_PRIORITY); //Name of the Thread : main and its Priority is : 1

//		Thread.currentThread().setPriority(Thread.NORM_PRIORITY);//Name of the Thread : main and its Priority is : 5

		System.out.println("Name of the Thread : " + Thread.currentThread().getName() + " and its Priority is : "
				+ Thread.currentThread().getPriority());

	}
}
