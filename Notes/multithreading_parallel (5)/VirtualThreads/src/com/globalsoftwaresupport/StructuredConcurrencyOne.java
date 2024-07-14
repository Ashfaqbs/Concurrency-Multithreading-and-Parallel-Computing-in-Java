package com.globalsoftwaresupport;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.concurrent.StructuredTaskScope.Subtask.State;

public class StructuredConcurrencyOne {

	public static void main(String[] args) throws InterruptedException {
		
		// we do not pool virtual threads: we create new ones for every task
		// and we dispose them after they finished
		try(var scope = new StructuredTaskScope<String>()) {
			
			var process1 = new LongProcess(1, "result 1");
			var process2 = new LongProcess(7, "result 2");
			
			// we submit the tasks in parallel
			Subtask<String> res1 = scope.fork(process1);
			Subtask<String> res2 = scope.fork(process2);
			
			// BECAUSE VIRTUAL THREADS !!!
			scope.join();
			
			// combine the results
			// get() will not block because the join() waits for the threads to finish
			
			if(res1.state() == State.SUCCESS)
				System.out.println(res1.get());
			
			if(res2.state() == State.SUCCESS)
				System.out.println(res2.get());
			
			// it will shutdown the scope after all child threads terminate
		}

	}

}
