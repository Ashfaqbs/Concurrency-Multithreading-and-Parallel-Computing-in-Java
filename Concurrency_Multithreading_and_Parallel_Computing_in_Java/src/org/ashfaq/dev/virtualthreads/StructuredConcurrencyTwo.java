package org.ashfaq.dev.virtualthreads;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

class LongProcessFail implements Callable<String> {

	private int timeToSleep;

	private String resultString;

	private boolean fail;

	public LongProcessFail(int timeToSleep, String resultString, boolean fail) {
		this.timeToSleep = timeToSleep;
		this.resultString = resultString;
		this.fail = fail;
	}

	@Override
	public String call() throws Exception {
		System.out.println("Starting thread " + resultString);
		Thread.sleep(Duration.ofSeconds(2));

		if (fail) {
			throw new Exception("Exception in thread " + resultString);
		}

		System.out.println("Ending thread " + resultString);
		return resultString;
	}
}

public class StructuredConcurrencyTwo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// 1. ShutdownOnFailure
//		--how to terminate  all other child threads when ever there is a exception
		// we dont pool vitrual threads , we create a new one for each task and we
		// dispose them after they finished
//		try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
//
//			var process1 = new LongProcessFail(3, "Result1", true);
//			var process2 = new LongProcessFail(7, "Result2", false);
//
//			// we submit the tasks in parallel
//
//			Subtask<String> subtask1 = scope.fork(process1);
//			Subtask<String> subtask2 = scope.fork(process2);
//			scope.join();
//			try {
//				scope.throwIfFailed();
//			} catch (ExecutionException e) {
//				System.out.println("Terminating all the threads	.......");
//				e.printStackTrace();
//			}
//
//			if ((subtask1.state() == State.SUCCESS && subtask2.state() == State.SUCCESS))
//				System.out.println(subtask1.get() + " " + subtask2.get());
//
//			// we can combine the results
//
//			// it will shutdown the scope after all child threads terminate
//		}

//		2. ShutdownOnSuccess<T>

		// imagine we need to temprature but the api's are not reliable
		// so we try to call 3 api simultaneously and when one is given the result we
		// need to stop
		// the other service
		// so we weill use shutdownonSuccess

		try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {

			var process1 = new LongProcessFail(3, "Result1", true);
			var process2 = new LongProcessFail(7, "Result2", false);

			// we submit the tasks in parallel

			Subtask<String> subtask1 = scope.fork(process1);
			Subtask<String> subtask2 = scope.fork(process2);
			scope.join();

			// we are not sure which service will give use the successful result
			String result = scope.result();
			System.out.println("Result: " + result);
//			Starting thread Result1
//			Starting thread Result2
//			Ending thread Result2
//			Result: Result2
//			if We see not all the threads are executed successfully as one gave a successful result

			// it will shutdown the scope after all child threads terminate
		}

	}
}
