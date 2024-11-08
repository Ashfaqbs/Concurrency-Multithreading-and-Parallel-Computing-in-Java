package com.ashfaq.example.bulkoperation;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ExecutorServiceConfig {

	@Bean(name = "plantaskExecutor")
	public Executor plantaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5); // Minimum number of threads
		executor.setMaxPoolSize(20); // Maximum number of threads
		executor.setQueueCapacity(100); // Queue size to hold tasks
		executor.setThreadNamePrefix("PLAN-TaskExecutor-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // Rejection policy
		executor.initialize();
		return executor;
	}
}
