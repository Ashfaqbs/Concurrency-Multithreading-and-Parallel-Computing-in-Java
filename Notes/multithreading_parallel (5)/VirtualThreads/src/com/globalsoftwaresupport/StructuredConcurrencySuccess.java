package com.globalsoftwaresupport;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.concurrent.StructuredTaskScope.Subtask.State;

public class StructuredConcurrencySuccess {

	public static void main(String[] args) throws InterruptedException {
		
		// we do not pool virtual threads: we create new ones for every task
		// and we dispose them after they finished
		try(var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
			
			var process1 = new LongProcessFail(1, "result 1", true);
			var process2 = new LongProcessFail(5, "result 2", true);
			
			// we submit the tasks in parallel
			Subtask<String> res1 = scope.fork(process1);
			Subtask<String> res2 = scope.fork(process2);
			
			// BECAUSE VIRTUAL THREADS !!!
			scope.join();
			
			String result = null;
			
			try {
				result = scope.result();
				System.out.println(result);
			} catch (ExecutionException e) {			
				System.out.println("There is no solution...");
			}	
		}

	}

}
