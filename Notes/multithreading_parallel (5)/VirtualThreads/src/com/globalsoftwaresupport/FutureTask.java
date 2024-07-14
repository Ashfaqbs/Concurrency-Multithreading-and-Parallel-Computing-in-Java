package com.globalsoftwaresupport;

import java.util.concurrent.Callable;

public class FutureTask implements Callable<String> {

	private String name;
	
	public FutureTask(String name) {
		this.name = name;
	}
	
	@Override
	public String call() throws Exception {
		System.out.println("Starting " + name);
		Thread.sleep(2000);
		System.out.println("Finishing " + name);
		return name;
	}
}
