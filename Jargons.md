
**Context switching** is the process of the CPU switching between threads, using a scheduling algorithm to manage which thread runs when. This allows multiple threads to share a single CPU efficiently, but can introduce overhead.
- Context switching occurs when the CPU switches from one thread to another, which can be resource-intensive and affect performance.


**Overhead** refers to the extra work or resources required to manage a process beyond the actual task at hand. In computing, it's like the "cost" of doing something extra that doesn't directly contribute to the task's output. For example, in multithreading, overhead includes the CPU time spent on context switching between threads.

**Throughput** is the amount of work done in a given time.
- Throughput is the amount of work or tasks completed in a specific time frame. In our cooking example, it would be the number of dishes you finish in an hour. The more dishes you complete, the higher the throughput.



A **bottleneck** is a point in a process where the flow of work is restricted, causing delays and reducing overall efficiency. It's like a traffic jam in a single lane on a busy road. Even if the rest of the road is clear, the traffic moves slowly because all cars must pass through that narrow point. In computing, a bottleneck could be a slow database query or a limited network connection that holds up the entire process.
- A bottleneck is any factor that slows down the overall process, even if individual tasks are running efficiently. It could be a network issue, a slow database, or any other resource that's causing a delay in the workflow.


Threads share the same memory space within a process. They have their own stack, which keeps track of method calls and local variables, but they share the heap, where objects and class-level variables reside.

**Resource sharing** in the context of multithreading refers to this shared memory space. Multiple threads can access and modify the same objects or variables. This can lead to issues like race conditions if not managed properly, where the outcome depends on the sequence or timing of thread execution. That's why synchronization mechanisms are used to control access to shared resources.
- Resource contention occurs when several threads or processes compete for the same resources, like CPU, memory, or I/O. This competition can lead to delays and reduced performance.
- Resource sharing refers to multiple threads within a process sharing the same memory space, particularly the heap, where objects and class-level variables are stored. Threads have their own stack for method calls and local variables but share access to objects and data in the heap, requiring careful synchronization to avoid conflicts.

