package com.ashfaq.example.async.eg2;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Async("customExecutor") // Use custom executor defined in AsyncConfig
	public CompletableFuture<Void> processProductAsync() {
		try {
			System.out.println("Processing product on thread: " + Thread.currentThread().getName());
			Thread.sleep(6000); // Simulate a 6-second delay for processing
			System.out.println("Product processed on thread: " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			System.out.println("Processing interrupted: " + e.getMessage());
		}
		return CompletableFuture.completedFuture(null);
	}
}


//Error : call the api more then 16times before 6 secs then it will give error 
//2024-10-27T13:33:23.144+05:30[0;39m [31mERROR[0;39m [35m28912[0;39m [2m---[0;39m [2m[sb3-mt-samples]
//[nio-8080-exec-9][0;39m [2m[0;39m[36mo.a.c.c.C.[.[.[/].[dispatcherServlet]   [0;39m [2m:[0;39m Servlet.service() for servlet [dispatcherServlet]
//in context with path [] threw exception [Request processing failed: org.springframework.core.task.TaskRejectedException: ExecutorService in active state did not accept task: 
//java.util.concurrent.CompletableFuture$AsyncSupply@38326808] with root cause
//
//java.util.concurrent.RejectedExecutionException: Task java.util.concurrent.CompletableFuture$AsyncSupply@38326808
//rejected from org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor$1@19641e77[Running, pool size = 5, active threads = 5, queued tasks = 10, completed tasks = 2]