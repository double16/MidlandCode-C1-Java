# REST

The Spring REST controller can be used to build API's and return JSON based on Java objects.

The following examples are based on [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/).

The following class models what we want to return. It does no special work, only contains the data we want to return.

```java
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
```

The code responding to an HTTP call is the controller, defined by the `@RestController` annotation.

```java
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting", method=GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
```

The object returned by the `greeting` method will be converted to JSON by Spring.

```json
{"id":1,"content":"Hello, World!"}
```
