package org.ashfaq.dev.concepts.Concurrent_Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsdotSynchronizedmethod {

//	Vector , Stack are thread safe and we have  hash table as well
//	but its not synchronized best way possible so we are going to use concurrent hashmaps 
//	and we will see the concurrent collections 

//	but we can make them synchronize by using a //Collections.synchronized.....(null)
//but there is a draw back and eventually we have to move to concurrent collections 
	public static void main(String[] args) {

//		most of the collections classes except (vector and stack) are not synchronized i.e we can use them in single threaded
//		applications 
			

		List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
//		after adding the Collections.synchronizedList(new ArrayList<Integer>()); the add() and remove() methods are
//		synchronized
//		Intrinsic lock - not that efficient because threads have to wait for each other even when want to execute 
//		independent methods (operations )
		//Concurrent Collections is the efficient way here
		

		Thread t1 = new Thread(() -> {
			for (int i = 1; i <= 1000; i++) {
				list.add(i);
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 1; i <= 1000; i++) {
				list.add(i);
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("The size of array is  :" + list.size());

	}

	//Other synchronized methods explained 
	//syncMap , syncSet , syncCollection
	
	
	
	//error when not sync : 
//	Exception in thread "Thread-1" Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: Index 46 out of bounds for length 22
//	at java.base/java.util.ArrayList.add(ArrayList.java:484)
//	at java.base/java.util.ArrayList.add(ArrayList.java:496)
//	at Concurrency_Multithreading_and_Parallel_Computing_in_Java/org.ashfaq.dev.concepts.Concurrent_Collections.CollectionsdotSynchronizedmethod.lambda$0(CollectionsdotSynchronizedmethod.java:23)
//	at java.base/java.lang.Thread.run(Thread.java:1583)
//java.lang.ArrayIndexOutOfBoundsException: Index 46 out of bounds for length 22
//	at java.base/java.util.ArrayList.add(ArrayList.java:484)
//	at java.base/java.util.ArrayList.add(ArrayList.java:496)
//	at Concurrency_Multithreading_and_Parallel_Computing_in_Java/org.ashfaq.dev.concepts.Concurrent_Collections.CollectionsdotSynchronizedmethod.lambda$1(CollectionsdotSynchronizedmethod.java:29)
//	at java.base/java.lang.Thread.run(Thread.java:1583)
//The size of array is  :46
	
	//Explanation of error :
//	the ArrayIndexOutOfBoundsException is not expected because the ArrayList size is not limited. However, the issue arises when multiple threads concurrently access and modify the ArrayList without proper synchronization.
//
//	When multiple threads add elements to the ArrayList without synchronization, it can lead to race conditions. Each thread may not see the updated size of the list after another thread has added an element, resulting in an index that is out of bounds.
//
//	To fix this issue, you can use Collections.synchronizedList() to create a synchronized version of the ArrayList. This ensures that only one thread can modify the list at a time, preventing concurrent modifications and the resulting ArrayIndexOutOfBoundsException.
//	
	
//	The ArrayIndexOutOfBoundsException in your code is occurring because multiple threads are concurrently accessing and modifying the shared ArrayList.
//
//	In Java, when multiple threads modify a non-thread-safe data structure like ArrayList without proper synchronization, it can lead to race conditions where one thread is trying to read or write the list while another thread is simultaneously modifying it. This can result in unexpected behavior, including accessing indices that are out of bounds.
//
//	In your case, both Thread-0 and Thread-1 are adding elements to the ArrayList concurrently. Since ArrayList is not inherently thread-safe, the two threads can interfere with each other's operations, leading to the exception.
//
//	To prevent this issue, you can use Collections.synchronizedList() as shown in the previous code example. This method returns a synchronized (thread-safe) list backed by the specified list. This ensures that only one thread can modify the list at a time, preventing concurrent modifications and the resulting ArrayIndexOutOfBoundsException.
//
//	By synchronizing access to the shared ArrayList, you can avoid race conditions and ensure that the list is accessed and modified safely by multiple threads.
	
	
	

}
