package com.ashfaq.example.sample.eg1.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorConfig {

	@Bean
	public ExecutorService taskExecutor() {
		return Executors.newFixedThreadPool(5); // Set the pool size to 5
	}
}
