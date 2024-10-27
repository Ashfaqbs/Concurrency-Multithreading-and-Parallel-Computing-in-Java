package com.ashfaq.example.async.eg1;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

	@Value("${async.executor.corePoolSize}")
	private int corePoolSize;

	@Value("${async.executor.maxPoolSize}")
	private int maxPoolSize;

	@Value("${async.executor.queueCapacity}")
	private int queueCapacity;

	@Bean(name = "asyncExecutor")
	Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize); // Set from properties
		executor.setMaxPoolSize(maxPoolSize); // Set from properties
		executor.setQueueCapacity(queueCapacity); // Set from properties
		executor.setThreadNamePrefix("AsyncThread-");

		executor.initialize();
		return executor;
	}
}

/*
 The async configuration we added on top of the update API using `@Async`, where we defined parameters like core pool size, max pool size, and queue capacity,
how each of these configurations affects the async update behavior.

When we configure an async task executor with these settings, it influences how many threads are created and managed for handling concurrent tasks in the update API. Here's how each of those settings works:

1. **Core Pool Size**: This is the minimum number of threads that will stay alive in the thread pool, even if they're idle. So, when the async update API starts handling tasks,
 it initially uses up to the core pool size number of threads. If we set, for example, `corePoolSize = 5`, the executor starts with five threads ready to handle update requests.

2. **Max Pool Size**: This is the maximum number of threads allowed in the thread pool. When there’s a surge in update requests beyond the core pool size, the pool can grow up to this maximum size. If `maxPoolSize = 10`, 
the pool can create up to ten threads to handle the incoming update tasks. This flexibility helps us deal with sudden spikes in load.

3. **Queue Capacity**: This is the number of tasks that can be queued up while waiting for an available thread. 
Let’s say the core pool size threads are all busy, and the pool hasn’t reached the max size yet. New tasks go into this queue until threads are available.
 If this queue is full, and no threads can be created (since we hit `maxPoolSize`), then new tasks are rejected (which we can handle with a rejection policy if needed) shown with a eg in eg2 package.

So, when we call the async update API:
- Initially, tasks use the core pool of threads.
- If more tasks come in, the pool grows up to the max size.
- If all threads are busy and the queue is full, further tasks may be rejected or handled based on the policy we set.

This configuration lets us control concurrency, ensuring the system doesn’t create too many threads and overwhelm resources but can handle high loads efficiently.
*/
