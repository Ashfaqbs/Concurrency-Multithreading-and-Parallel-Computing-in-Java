we can define `Callable` objects using different approaches. Here are a few variations:

---

### 1. Using a Separate Callable Class

Instead of using lambdas, you can create a separate class that implements `Callable`. This approach is more structured and reusable, especially if the task is complex.

```java
import java.util.concurrent.Callable;

class MyTask implements Callable<String> {
    private final String taskName;

    public MyTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(3000); // Simulate work
        return taskName + " completed";
    }
}

// Usage
taskList.add(new MyTask("Task 1"));
taskList.add(new MyTask("Task 2"));
taskList.add(new MyTask("Task 3"));
```

In this case, each `MyTask` instance is initialized with a task name and then added to the `taskList`.

---

### 2. Using Anonymous Callable Class

You can also use an anonymous class if you prefer not to create a named class, but want more structure than a lambda provides.

```java
taskList.add(new Callable<String>() {
    @Override
    public String call() throws Exception {
        Thread.sleep(3000); // Simulate work
        return "Anonymous Task completed";
    }
});
```

---

### 3. Using Method References (if the task can be represented by a method)

If you have a method that performs the required task and returns a `String`, you can reference that method as a `Callable`.

```java
public class MyTaskHandler {
    public String executeTask() throws InterruptedException {
        Thread.sleep(3000); // Simulate work
        return "Method Reference Task completed";
    }
}

// Usage
MyTaskHandler handler = new MyTaskHandler();
taskList.add(handler::executeTask);
```

Here, `handler::executeTask` is a method reference that fits the `Callable<String>` interface, so it can be directly added to the `taskList`.



### 4. Using Lambda Expressions


```

public static void main(String[] args) {
		// Create an ExecutorService with a fixed thread pool
		ExecutorService executorService = Executors.newFixedThreadPool(3);

//		() -> {
//			Thread.sleep(1000); // Simulate work
//			return "Task 1 completed";
//		 this is a callable object returning a string

		// Create a list of Callable tasks
		List<Callable<String>> taskList = new ArrayList<>();
		taskList.add(() -> {
			Thread.sleep(1000); // Simulate work
			return "Task 1 completed";
		});
		taskList.add(() -> {
			Thread.sleep(2000); // Simulate work
			return "Task 2 completed";
		});
		taskList.add(() -> {
			Thread.sleep(3000); // Simulate work
			return "Task 3 completed";
		});

		try {
			// Execute all tasks and wait for them to complete
			List<Future<String>> futures = executorService.invokeAll(taskList);

			// Loop through each Future to get the results
			for (Future<String> future : futures) {
				// Future.get() will block until the task completes
				System.out.println("Result: " + future.get());
			}

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			// Shut down the executor
			executorService.shutdown();
		}
	}
	
```



---

Each of these approaches can be used depending on the structure and reusability you need for the tasks.