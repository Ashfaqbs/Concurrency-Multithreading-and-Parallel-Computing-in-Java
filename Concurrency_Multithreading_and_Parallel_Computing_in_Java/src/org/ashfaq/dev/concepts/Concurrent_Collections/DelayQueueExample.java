package org.ashfaq.dev.concepts.Concurrent_Collections;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class DelayedTask implements Delayed {
    private final String name;
    private final long delayTime;
    private final long expireTime;

    public DelayedTask(String name, long delayTime) {
        this.name = name;
        this.delayTime = delayTime;
        this.expireTime = System.currentTimeMillis() + delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = expireTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.expireTime < ((DelayedTask) o).expireTime) {
            return -1;
        }
        if (this.expireTime > ((DelayedTask) o).expireTime) {
            return 1;
        }
        return 0;
    }

    public void execute() {
        System.out.println("Executing task: " + name + " at " + System.currentTimeMillis());
    }
}

public class DelayQueueExample {
    public static void main(String[] args) {
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // Add tasks to the delay queue
        delayQueue.put(new DelayedTask("Task1", 5000)); // 5 seconds delay
        delayQueue.put(new DelayedTask("Task2", 3000)); // 3 seconds delay
        delayQueue.put(new DelayedTask("Task3", 10000)); // 10 seconds delay

        // Consumer thread to execute tasks after their delay
        executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    DelayedTask task = delayQueue.take(); // Blocks until a task's delay has expired
                    task.execute();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // For demonstration, we'll shut down the executor after some time
        try {
            Thread.sleep(15000); // Let it run for 15 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executor.shutdownNow();
    }
}
