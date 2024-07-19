package org.ashfaq.dev.virtualthreads;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.concurrent.StructuredTaskScope.Subtask.State;

class LongProcess implements Callable<String> {

	private int timeToSleep;

	private String resultString;

	public LongProcess(int timeToSleep, String resultString) {
		this.timeToSleep = timeToSleep;
		this.resultString = resultString;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(Duration.ofSeconds(2));
		return resultString;
	}
}

public class StructuredConcurrencyOne {
	public static void main(String[] args) {

		// we dont pool vitrual threads , we create a new one for each task and we
		// dispose them after they finished
		try (var scope = new StructuredTaskScope<String>();) {

			var process1 = new LongProcess(3, "Result1");
			var process2 = new LongProcess(7, "Result2");

			// we submit the tasks in parallel

			Subtask<String> subtask1 = scope.fork(process1);
			Subtask<String> subtask2 = scope.fork(process2);

			scope.join();

			if ((subtask1.state() == State.SUCCESS && subtask2.state() == State.SUCCESS))
				System.out.println(subtask1.get() + " " + subtask2.get());

			// we can combine the results

			// it will shutdown the scope after all child threads terminate
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
