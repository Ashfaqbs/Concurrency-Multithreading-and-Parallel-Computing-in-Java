package org.ashfaq.dev.rentrantlock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(ReentrantLockExample::doTask).start();
        new Thread(ReentrantLockExample::doTask).start();
    }

    public static void doTask() {
        lock.lock(); // Acquire the lock
        try {
            System.out.println(Thread.currentThread().getName() + " is executing the task.");
            // This method can be called recursively since it already holds the lock
            recursiveMethod(5);
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    public static void recursiveMethod(int count) {
        lock.lock(); // Acquire the lock again (reentrant)
        try {
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + " is inside the recursive method: " + count);
                recursiveMethod(count - 1); // Recursive call
            }
        } finally {
            lock.unlock(); // Release the lock
        }
    }
}
