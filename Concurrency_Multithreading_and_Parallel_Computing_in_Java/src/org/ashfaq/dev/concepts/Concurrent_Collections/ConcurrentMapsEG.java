package org.ashfaq.dev.concepts.Concurrent_Collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
//***IMP
//by default the hashmaps are not synchronized
// to make it synchronized use collections.synchronizedMap(map) but this is not
// efficient as there would be a intrinsic lock and this would be like
// where there is dependency between the threads the threads will wait but when
// there are independent threads then also the threads will wait.

class MapFirstWorker implements Runnable {

	private ConcurrentMap<String, Integer> conMap;

	public MapFirstWorker(ConcurrentMap<String, Integer> conMap) {
		this.conMap = conMap;
	}

	@Override
	public void run() {
		try {
			conMap.put("B", 12);
			Thread.sleep(3000);
			conMap.put("A", 5);
			conMap.put("Z", 25);
			Thread.sleep(2000);
			conMap.put("D", 19);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class MapSecondWorker implements Runnable {

	private ConcurrentMap<String, Integer> conMap;

	public MapSecondWorker(ConcurrentMap<String, Integer> conMap) {
		this.conMap = conMap;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
			System.out.println(conMap.get("A"));
			Thread.sleep(2000);
			System.out.println(conMap.get("Z"));
			System.out.println(conMap.get("B"));

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

public class ConcurrentMapsEG {

	// by default the hashmaps are not synchronized
	// to make it synchronized use collections.synchronizedMap(map) but this is not
	// efficient as there would be a intrinsic lock and this would be like
	// where there is dependency between the threads the threads will wait but when
	// there are independent threads then also the threads will wait.

	public static void main(String[] args) {

		ConcurrentMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();

		Thread t1 = new Thread(new MapFirstWorker(map));

		Thread t2 = new Thread(new MapSecondWorker(map));

		t1.start();
		t2.start();

	}

}
//synchronization is not that straight forward we have to use sync blocks , locks , we have to avoid thread starvation,  if we are using the built in classes this will be taken care 
//and this are tested as well
