# Cheat Sheet
## Concurrent Data Structures

### 1. ConcurrentHashMap
- **Usage**: A thread-safe variant of `HashMap`.
- **Example**:
  ```
  ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
  map.put("key", 1);
  int value = map.get("key");
  
### 2. CopyOnWriteArrayList
- **Usage**:  A thread-safe variant of ArrayList for read-mostly scenarios.
- **Example**:
```
List<String> list = new CopyOnWriteArrayList<>();
list.add("element");
for (String s : list) {
    System.out.println(s);
}
```

### 3. BlockingQueue
- **Usage**:  Thread-safe queues that block on operations when necessary.

Types:
ArrayBlockingQueue
LinkedBlockingQueue
PriorityBlockingQueue
- **Example**:
```
BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
queue.put("element");
String element = queue.take();
```

### 4. ConcurrentSkipListMap
Usage: A thread-safe variant of TreeMap for scalable sorted maps.
- **Example**:

```
ConcurrentNavigableMap<String, Integer> map = new ConcurrentSkipListMap<>();
map.put("key", 1);
int value = map.get("key");
```

###  5. DelayQueue
Usage: A thread-safe queue that holds elements until a delay has expired.
- **Example**:
```
DelayQueue<DelayedElement> queue = new DelayQueue<>();
queue.put(new DelayedElement());
DelayedElement element = queue.take();
```

## Using ExecutorService

Creating an ExecutorService
```
Fixed Thread Pool:

ExecutorService executor = Executors.newFixedThreadPool(5);

Single Thread Executor:

ExecutorService executor = Executors.newSingleThreadExecutor();

Cached Thread Pool:

ExecutorService executor = Executors.newCachedThreadPool();

Scheduled Thread Pool:

ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
```

Submitting Tasks

```
Runnable Task:

executor.submit(() -> {
    System.out.println("Task executed");
});

Callable Task:

Future<String> future = executor.submit(() -> {
    return "Task result";
});
```

Shutting Down ExecutorService
Shutdown gracefully:

```
executor.shutdown();
if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
    executor.shutdownNow();
}

Force shutdown:


executor.shutdownNow();

```

## Good Practices

1. Use Appropriate Data Structures
Choose thread-safe data structures from the java.util.concurrent package.
Example: Use ConcurrentHashMap instead of HashMap.

3. Minimize Locking
Prefer using higher-level concurrency utilities like Semaphore, CountDownLatch, CyclicBarrier over explicit locks.
Example:
```
Semaphore semaphore = new Semaphore(1);
semaphore.acquire();
try {
    // critical section
} finally {
    semaphore.release();
}
```

3. Use Executors for Thread Management
Avoid manual thread creation; use ExecutorService for better management.
Example:
```
ExecutorService executor = Executors.newFixedThreadPool(5);
executor.submit(() -> {
    // task
});
executor.shutdown();
```

4. Handle Exceptions in Tasks
Ensure that tasks submitted to an executor service handle exceptions properly.
Example:
```
executor.submit(() -> {
    try {
        // task
    } catch (Exception e) {
        e.printStackTrace();
    }
});
```

5. Properly Shutdown ExecutorService
Always shutdown ExecutorService to release resources.
Example:
```
executor.shutdown();
try {
    if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
        executor.shutdownNow();
    }
} catch (InterruptedException e) {
    executor.shutdownNow();
}
```
6. Avoid Blocking Operations in Tasks
Avoid long-running or blocking operations inside tasks to keep the thread pool responsive.
Example:

```
executor.submit(() -> {
    // Avoid blocking calls like Thread.sleep() or I/O operations
});
```

7. Use Thread-Safe Collections
Use collections from java.util.concurrent for thread safety.
Example:
```
ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
```

