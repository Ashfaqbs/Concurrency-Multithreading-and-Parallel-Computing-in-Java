# Migrating from Tomcat to Netty in Spring Boot Applications

## Overview

Spring Boot applications by default use Tomcat as the embedded servlet container when using `spring-boot-starter-web`. Replacing Tomcat with Netty requires a shift from Spring MVC (servlet-based stack) to Spring WebFlux (reactive stack). This document outlines the steps required to perform this migration, explains the behavioral differences between the two server stacks, and discusses the limitations and implications of using both Spring Web and Spring WebFlux dependencies in the same application.

---

## 1. Replacing Tomcat with Netty

### 1.1. Required Changes

To migrate from Tomcat to Netty, the application must move from the traditional Spring MVC model to the reactive programming model provided by Spring WebFlux.

### Maven Configuration

**Remove** the Spring Web dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

**Add** the Spring WebFlux dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

WebFlux uses Netty by default as the embedded server through `spring-boot-starter-reactor-netty`.

### Gradle Configuration

**Remove**:

```groovy
implementation 'org.springframework.boot:spring-boot-starter-web'
```

**Add**:

```groovy
implementation 'org.springframework.boot:spring-boot-starter-webflux'
```

### 1.2. Verifying Server Startup

Upon successful configuration, the application logs will indicate that Netty is the embedded server:

```
Netty started on port(s): 8080
```

### 1.3. Controller Style in WebFlux

```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Mono<String> sayHello() {
        return Mono.just("Hello from Netty!");
    }
}
```

Spring WebFlux requires controllers to return `Mono` or `Flux` types to support non-blocking reactive operations.

---

## 2. Mixing Spring MVC and Spring WebFlux

### 2.1. Behavior When Both Are Present

Including both `spring-boot-starter-web` and `spring-boot-starter-webflux` in the project will cause Spring Boot to default to the Spring MVC configuration. This occurs because Spring Boot prefers the servlet-based stack (`DispatcherServlet`) if it is available.

This means:

* Tomcat will be used as the embedded server.
* WebFlux-specific components such as `WebHandler` will be ignored.
* Reactive controllers may not behave as expected.
* Potential for startup conflicts and ambiguous mappings.

### 2.2. Conclusion

Mixing both stacks in a single application is not recommended unless a hybrid architecture is explicitly required and carefully configured.

---

## 3. Asynchronous APIs in Spring MVC vs Reactive APIs in WebFlux

### 3.1. Asynchronous Support in Spring MVC (Tomcat)

Spring MVC allows asynchronous request handling using constructs such as `@Async`, `Callable`, `DeferredResult`, and `CompletableFuture`.

Example:

```java
@GetMapping("/async")
public Callable<String> asyncHello() {
    return () -> {
        Thread.sleep(1000);
        return "Hello from async MVC!";
    };
}
```

This enables non-blocking behavior at the controller level, but the underlying servlet container (Tomcat) still uses a blocking thread-per-request model.

### 3.2. Reactive Programming in Spring WebFlux (Netty)

WebFlux supports fully non-blocking, reactive APIs using Reactor types.

Example:

```java
@GetMapping("/reactive")
public Mono<String> helloReactive() {
    return Mono.just("Hello from reactive Netty!");
}
```

This model supports backpressure, is optimized for I/O-bound workloads, and scales efficiently due to its event-loop-based threading model.

---

## 4. Technical Comparison: Spring MVC (Tomcat) vs Spring WebFlux (Netty)

| Feature                      | Spring MVC (Tomcat)                         | Spring WebFlux (Netty)  |
| ---------------------------- | ------------------------------------------- | ----------------------- |
| Thread Model                 | One thread per request                      | Event-loop, few threads |
| Async Handling               | Callable, DeferredResult, CompletableFuture | Mono, Flux (Reactor)    |
| Embedded Server              | Tomcat (blocking)                           | Netty (non-blocking)    |
| Suitability for Reactive DBs | Not suitable                                | Optimized               |
| Backpressure Support         | Not supported                               | Supported               |
| Scalability                  | Limited by thread pool                      | High scalability        |
| Monitoring & Debugging       | Simpler                                     | More complex            |
| Learning Curve               | Lower                                       | Higher                  |

---

## 5. Threading and Scaling Considerations

### 5.1. Tomcat and Thread-Per-Request Model

Tomcat allocates one thread per request. With a fixed-size thread pool (e.g., 200 threads), only 200 concurrent requests can be processed. Blocking operations, such as JDBC calls, tie up threads and reduce scalability. Async processing offloads work but still relies on manually configured thread pools.

### 5.2. Netty and Event-Loop Model

Netty uses a small number of threads (typically 2× CPU cores) and an event-loop model. This enables thousands of concurrent connections without the overhead of large thread pools. Non-blocking operations do not tie up worker threads, making the system more scalable and efficient.

---

## 6. Summary and Recommendation

* Applications requiring reactive behavior and high concurrency should use **Spring WebFlux with Netty**.
* Applications using blocking libraries or traditional web features should use **Spring MVC with Tomcat**.
* Mixing both Spring Web and Spring WebFlux leads to ambiguous configurations and should be avoided unless absolutely necessary.
* While asynchronous APIs can be implemented using Spring MVC, they are not as scalable or efficient as fully reactive solutions using WebFlux.

---
### **Understanding Backpressure and Event Loop: A Layman's Analogy + Technical Explanation**

---

##  1. Layman's Analogy

### **Backpressure: The Kitchen & Waiter Analogy**

* **Scenario**: A busy restaurant.
* **Customer**: Sends orders (requests) to the kitchen.
* **Kitchen**: Prepares food (processes requests).
* **Waiter**: Brings orders from the customer to the kitchen.

#### Without Backpressure:

* The waiter keeps bringing in **more orders** to the kitchen **regardless** of how fast the kitchen can cook.
* Eventually, the kitchen gets overwhelmed: food is delayed or messed up.
* Some orders may be **lost**, **cancelled**, or customers may **leave** frustrated.

#### With Backpressure:

* The waiter **monitors** the kitchen’s capacity.
* If the kitchen is too busy, the waiter tells customers to **wait** or slows down how fast they take new orders.
* The kitchen works **smoothly**, customers may wait longer but still get their food.

### **Event Loop: The Call Bell Analogy**

* Imagine the **kitchen only has one chef**.
* Instead of staying with one dish (request), the chef **does a little bit of each**:

  * Starts boiling pasta.
  * Switches to stir-frying veggies.
  * Then checks the oven.
* Whenever a task is **waiting (e.g., boiling water)**, the chef moves on to the next.
* The chef is **always busy**, but **never blocked**, because they switch between tasks that are **waiting**.

---

##  2. Technical Explanation

### **Backpressure (in Reactive Systems)**

* **Definition**: A mechanism that allows a consumer to signal to the producer to **slow down** or **stop sending data** until it’s ready again.
* **Purpose**: Prevents the system from being overwhelmed by too much data.
* **In Reactive Streams**: The consumer explicitly requests how much data it can handle (`request(n)`).
* **Example**: A streaming API produces data faster than a database can write it. Without backpressure, memory fills up; with backpressure, the producer waits.

### **Event Loop (in Netty or Node.js-style I/O)**

* **Definition**: A single-threaded loop that handles multiple I/O operations **asynchronously** using callbacks or non-blocking code.
* **Instead of waiting** for I/O (disk, DB, network), the event loop **delegates the task** and moves to the next one.
* **Returns** to handle the result when it's ready (like the chef returning to pasta once it's boiled).
* **Used in**: Netty, Node.js, libuv, etc.

---

##  Comparison of Layman's Analogy and Technical Concepts

| Concept         | Layman’s View                         | Technical Explanation                                    |
| --------------- | ------------------------------------- | -------------------------------------------------------- |
| Backpressure    | Waiter telling customers to slow down | Consumer signals producer on how much data it can handle |
| No Backpressure | Kitchen gets overloaded               | Memory fills, system crashes or drops data               |
| Event Loop      | Chef switching between tasks          | Non-blocking loop managing I/O using callbacks           |
| Blocking Model  | Chef waits for pasta to boil          | Thread stuck waiting for disk, DB, or network            |
| Non-blocking    | Chef moves on while pasta boils       | Thread frees up; I/O happens in the background           |

---

##  Why This Matters in Web Servers

| Feature            | Traditional Tomcat (Blocking) | Netty with WebFlux (Non-Blocking)            |
| ------------------ | ----------------------------- | -------------------------------------------- |
| Thread usage       | One per request               | Few threads serve many requests              |
| Blocking I/O       | Yes                           | No (asynchronous I/O)                        |
| Backpressure       | No built-in support           | Native support in reactive streams           |
| Scaling under load | Limited                       | Efficient with thousands of concurrent users |

---

##  Conclusion

* **Backpressure** ensures systems stay stable under heavy load by controlling the flow of data.
* **Event loop** enables a few threads to efficiently handle many concurrent I/O-bound tasks without blocking.
* When used together (e.g., in Netty + WebFlux), they enable highly scalable, responsive, and memory-efficient web applications.

