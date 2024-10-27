package com.ashfaq.example.async.eg2;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync // Enables async processing for methods annotated with @Async
public class AsyncConfig {

	@Bean(name = "customExecutor")
	public Executor customExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2); // Initial thread pool size
		executor.setMaxPoolSize(5); // Maximum thread pool size
		executor.setQueueCapacity(10); // Queue size before rejecting tasks
		executor.setThreadNamePrefix("AsyncThread-");

		// Define the rejection policy
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

		// Initialize the executor
		executor.initialize();
		return executor;
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