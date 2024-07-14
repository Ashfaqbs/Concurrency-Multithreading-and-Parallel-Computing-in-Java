package com.globalsoftwaresupport;

import java.time.Duration;
import java.util.concurrent.Callable;

public class LongProcess implements Callable<String> {

	private int timeToSleep;
	private String result;
	
	public LongProcess(int timeToSleep, String result) {
		this.timeToSleep = timeToSleep;
		this.result = result;
	}
	
	@Override
	public String call() throws Exception {
		Thread.sleep(Duration.ofSeconds(timeToSleep));
		return result;
	}

}
