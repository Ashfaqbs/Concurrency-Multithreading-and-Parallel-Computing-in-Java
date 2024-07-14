package com.globalsoftwaresupport;

import java.time.Duration;
import java.util.concurrent.Callable;

public class LongProcessFail implements Callable<String> {

	private int timeToSleep;
	private String result;
	private boolean fail;
	
	public LongProcessFail(int timeToSleep, String result, boolean fail) {
		this.timeToSleep = timeToSleep;
		this.fail = fail;
		this.result = result;
	}
	
	@Override
	public String call() throws Exception {
		System.out.println("Starting thread " + result);
		Thread.sleep(Duration.ofSeconds(timeToSleep));
		
		if(fail) {
			System.out.println("Failure in child thread: " + result);
			throw new RuntimeException("Error");
		}
			
		System.out.println("Finish thread: " + result);
		return result;
	}

}
