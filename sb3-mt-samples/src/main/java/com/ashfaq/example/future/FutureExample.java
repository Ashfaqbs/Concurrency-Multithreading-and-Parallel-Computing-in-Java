package com.ashfaq.example.future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Create an ExecutorService with a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // Create a Callable task that returns a message after a delay
        Callable<String> task = () -> {
            Thread.sleep(2000); // Simulate delay
            return "Task completed!";
        };

        // Submit the task and receive a Future object
        Future<String> future = executorService.submit(task);

        // Perform other work while task is running...

        // Now, retrieve the result
        if (future.isDone()) {
            System.out.println("Result: " + future.get()); // This will block if task isn't done
        } else {
            System.out.println("Task is still running...");
        }

        // Shutdown the executor service
        executorService.shutdown();
    }
}

