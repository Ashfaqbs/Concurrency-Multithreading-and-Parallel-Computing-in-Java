package com.globalsoftwaresupport;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.concurrent.StructuredTaskScope.Subtask.State;

public class StructuredConcurrencyFailure {

	public static void main(String[] args) throws InterruptedException {
		
		// we do not pool virtual threads: we create new ones for every task
		// and we dispose them after they finished
		try(var scope = new StructuredTaskScope.ShutdownOnFailure()) {
			
			var process1 = new LongProcessFail(1, "result 1", true);
			var process2 = new LongProcessFail(7, "result 2", false);
			
			// we submit the tasks in parallel
			Subtask<String> res1 = scope.fork(process1);
			Subtask<String> res2 = scope.fork(process2);
			
			// BECAUSE VIRTUAL THREADS !!!
			scope.join();
			
			// if there is a failure in any of the child threads: other threads will be terminated
			try {
				scope.throwIfFailed();
				System.out.println(res1.get() + " - " + res2.get());
			} catch (ExecutionException e) {
				System.out.println("Terminating all the threads...");
			}
		
			// combine the results
			// get() will not block because the join() waits for the threads to finish	
			
			
			// it will shutdown the scope after all child threads terminate
		}

	}

}
