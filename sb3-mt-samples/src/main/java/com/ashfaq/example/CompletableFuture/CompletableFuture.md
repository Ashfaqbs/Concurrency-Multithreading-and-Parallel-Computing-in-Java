# CompletableFuture What And Why : 

## The main idea:

1. **Runnable and Callable in ExecutorService:**
   - **Runnable:** If we have a task that **doesn't need to return a result**, we use `Runnable`. It’s simply a piece of code that’s executed without returning anything.
   - **Callable:** For tasks that **need to return a result**, `Callable` is preferred because it allows we to return a value or throw checked exceptions.
   - **ExecutorService.submit()** can handle both `Runnable` and `Callable` tasks. If we submit a `Callable`, it returns a `Future`, which can be used to retrieve the result.

2. **The Limitation of Future (Blocking Behavior):**
   - When we use `Future` to get the result of a `Callable`, calling `.get()` on that `Future` **blocks the main thread** until the result is available.
   - If we have multiple tasks and use a loop to call `.get()` on each `Future`, our program can end up waiting (blocking) on each result sequentially, which can be inefficient.

3. **Non-Blocking with CompletableFuture:**
   - `CompletableFuture` was introduced to address the **non-blocking** issue with `Future`.
   - It allows we to start an asynchronous task and **chain further actions** without blocking. we don’t need to call `.get()` and block the thread waiting for the result.
   - With `CompletableFuture`, we can use methods like `.thenApply()`, `.thenAccept()`, and `.thenCompose()` to **handle the result asynchronously**, enabling truly non-blocking execution.

### Why CompletableFuture is Preferred:
   - It **avoids blocking** by using callbacks (i.e., `thenApply`, `thenAccept`) that are triggered once the result is ready, allowing our code to continue executing other tasks.
   - It simplifies **chaining tasks** or **combining results** from multiple asynchronous computations, which can get messy with `Future`.

### Key Advantage Summary:
   - **Runnable**: Use when there's no result expected.
   - **Callable**: Use with `Future` for a result, but it’s blocking.
   - **CompletableFuture**: Use for **non-blocking** execution when we need the result or further processing.




# 0 
### `CompletableFuture` can work with an `ExecutorService` to control the thread pool used for its tasks. By default, if we use methods like `supplyAsync()` or `runAsync()` without specifying an executor, `CompletableFuture` uses the **ForkJoinPool.commonPool**, which is a shared pool of threads.

### Using ExecutorService with CompletableFuture
If we want more control over threading (like custom thread pool sizes, thread priorities, or to avoid overloading the common pool), we can supply wer own `ExecutorService` when creating the `CompletableFuture`.

Here's how it looks:

```java
// Custom ExecutorService with a fixed thread pool
ExecutorService customExecutor = Executors.newFixedThreadPool(5);

// CompletableFuture with custom ExecutorService
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    // Task logic here
    return "Task result";
}, customExecutor);
```

### Benefits of Using a Custom Executor
1. **Resource Control**: we can set limits on the number of threads, so the tasks don’t consume more resources than we intend.
2. **Isolation**: Keeps wer `CompletableFuture` tasks separate from other unrelated tasks that might also be using the common pool.
3. **Fine-Tuned Performance**: If certain tasks need fewer or more threads, we can configure the thread pool size and other properties to optimize performance.

### Practical Usage Example
Consider a scenario where we want to handle a high volume of tasks (e.g., processing user uploads) and want to ensure they run efficiently without affecting other parts of the system.

```java
import java.util.concurrent.*;

public class CompletableFutureWithCustomExecutor {
    public static void main(String[] args) {
        // Define a custom ExecutorService
        ExecutorService customExecutor = Executors.newFixedThreadPool(10);
        
        // Using the custom executor with CompletableFuture
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Running task in custom executor - Thread: " + Thread.currentThread().getName());
        }, customExecutor);
        
        future.thenRunAsync(() -> System.out.println("Continuation of task in custom executor"), customExecutor);
        
        // Ensure the executor service is shut down after the tasks complete
        future.thenRun(customExecutor::shutdown);
    }
}
```

This example shows:
- **Task execution in a custom executor**, where `CompletableFuture` uses threads from the custom pool instead of the default.
- **Shutting down the executor** gracefully after task completion, which is crucial to free up resources.

### Summary
Yes, we can and often should use an `ExecutorService` with `CompletableFuture` if we want more control over concurrency. This setup is especially helpful in high-demand systems where performance tuning is essential.

# 1
If we don't define threads for the `ExecutorService` or `CompletableFuture`, Java uses the **ForkJoinPool.commonPool** by default for `CompletableFuture` tasks. Here’s what that means and how it works:

### What is the ForkJoinPool.commonPool?
The `ForkJoinPool.commonPool` is a shared, general-purpose thread pool provided by the `ForkJoinPool` class. This pool is available to the entire application and is used as the default thread pool for many parallel tasks in Java, including those created by `CompletableFuture`.

### Default Behavior Without Defining Threads
1. **Thread Pool Size**: The default `ForkJoinPool.commonPool` uses a number of threads equal to the **number of available processors (CPU cores)** on wer machine minus one. For instance, if wer machine has 8 CPU cores, it will use a pool of 7 threads by default.
  
2. **Task Execution**: If more tasks are submitted than there are available threads, the additional tasks **wait** until a thread becomes available. This means that tasks may get queued up and processed as threads complete existing tasks.

3. **Blocking**: Since the `commonPool` is shared among different tasks across the entire application, if one task blocks (e.g., waiting on I/O or network response), it may tie up one of the threads, which can affect other tasks that use the pool.

4. **Limitations**: For high-throughput or I/O-heavy tasks, the limited number of threads in `commonPool` can lead to performance bottlenecks, especially since it’s shared with other parts of the application.

### Potential Issues with Using Default Pool
- **Resource Contention**: If multiple components of wer application use the `commonPool` heavily, they may contend for threads, leading to slowdowns.
- **Non-dedicated Threads**: Since the threads are shared across the application, there’s a chance of interference with other parts of the system, especially if we have tasks with high CPU or memory needs.
- **Reduced Control**: we can’t easily change the size or behavior of the `commonPool` during runtime, which limits wer control over resource allocation.

### Example of Default Behavior
Here’s what it looks like without specifying an executor:

```java
CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
    System.out.println("Task running in default pool: " + Thread.currentThread().getName());
});
```

In this example:
- The task runs on one of the threads in the `ForkJoinPool.commonPool`.
- we won’t see the thread pool size or any specific configuration unless we change the JVM settings.

### When to Define wer Own Executor
- **Heavy Workloads**: If wer application performs many concurrent tasks, defining wer own executor gives we better control and isolation for wer tasks.
- **Non-blocking Tasks**: If tasks might block for a long time (e.g., waiting on external resources), a custom executor with more threads than available CPU cores can prevent the system from stalling.
- **Scalability**: Custom executors allow fine-tuning for the specific workload, enabling better resource utilization, stability, and performance.

### Conclusion
While using the `ForkJoinPool.commonPool` is convenient, it’s not ideal for high-demand, I/O-bound, or specialized tasks. Creating a custom `ExecutorService` for `CompletableFuture` gives we more control, prevents resource contention, and can optimize our application’s concurrency handling.
