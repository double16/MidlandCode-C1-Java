# Logging

Logging is an important part of an application. It provides history to help debug errors or other events.

Previously we've used `System.out.println`. That approach is ok for small programs but there is a better way. Java has several options for logging. We're going to use Lombok to provide a `log` object to each class in which we want to log.

```java
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreetingController {
    @RequestMapping("/api/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        log.info("greeting called");
        // ...
    }
}
```

The `log` object above is defined by Lombok using the `@Slf4j` annotation. `Slf4j` means "Simple Logging Framework For (4) Java". Logging frameworks aren't all that simple, but we'll keep it that way. There are many destinations for log messages: file, shared 'log' servers, databases, etc. We'll let Spring and Lombok handle that for us.

## Levels

| Level | Description                                                |
| ----- | ---------------------------------------------------------- |
| ERROR | Bad things that someone needs to look at soon              |
| WARN  | Potential problems                                         |
| INFO  | Normal events                                              |
| DEBUG | More details, but should only be turned on when necessary  |
| TRACE | Very much details, could fill up the filesystem            |

## Parameters

Logging can be expensive so you don't want to add strings together. When you want to show parameters, do the following:

```java
log.info("Adding a new entity {}", person);
```

If the log message is output, then the person.toString() is called. Seems small, but when there is lots of log messages it makes a difference.
