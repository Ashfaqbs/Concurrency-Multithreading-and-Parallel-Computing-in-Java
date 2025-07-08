- Send email **asynchronously and non-blocking** via SMTP in a **Spring Boot WebFlux** application.

---

## Key Notes

* SMTP clients (like JavaMailSender) are typically **blocking**.
* To use them in a **non-blocking WebFlux context**, run the blocking code on a **separate scheduler** (`Schedulers.boundedElastic()`).
* This ensures that **WebFlux event loop threads are not blocked**.

---

## 1. **Dependencies**

Add these to `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

---

## 2. **Email Configuration (`application.yml`)**

```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-password
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```

---

## 3. **Mail Service Using WebFlux (Non-Blocking)**

```java
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public Mono<Void> sendEmail(String to, String subject, String body) {
        return Mono.fromRunnable(() -> {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(body, true);
                mailSender.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException("Failed to send email", e);
            }
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }
}
```

* `Mono.fromRunnable(...)`: Wraps the blocking logic.
* `.subscribeOn(Schedulers.boundedElastic())`: Offloads to a thread pool designed for blocking operations.

---

## 4. **Controller Example**

```java
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/mail")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public Mono<String> sendMail(@RequestParam String to,
                                 @RequestParam String subject,
                                 @RequestParam String body) {
        return emailService.sendEmail(to, subject, body)
                .thenReturn("Mail sent asynchronously (non-blocking)");
    }
}
```

---

##  Summary

* JavaMailSender is blocking, but wrapping it inside `Mono.fromRunnable()` and scheduling it on `boundedElastic()` allows integration into a **non-blocking WebFlux** application.
* This pattern is standard in WebFlux when dealing with **legacy/blocking APIs** (like JDBC, file I/O, or SMTP).
