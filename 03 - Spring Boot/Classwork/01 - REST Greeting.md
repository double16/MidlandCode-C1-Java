# REST Greeting

Create a Spring Boot application that returns a greeting based on [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/).

## Create the Spring Boot Application

```shell
$ spring init -d=h2,jdbc,jpa,actuator,cache,devtools,hateoas,lombok -t gradle-project -a simple-rest --description "Spring Boot REST" -g edu.midlandu.codeacademy -j 11 -p jar simple-rest
Using service at https://start.spring.io
Project extracted to '/Users/double/simple-rest'
```

Import the application into IntelliJ.

1. `New > Project from Existing Sources`
2. Choose the new `simple-rest` folder
3. Choose `Import project from external model`
4. Choose `Gradle`
5. Click `Next`
6. Change Gradle JVM to `11` (or similar)

## Verify It Works

1. Click the `Gradle` tab in the right side of IntelliJ
2. Expand `Tasks > application`
3. Double-click `bootRun`
4. Open a browser
5. Visit <http://localhost:8080/actuator/health>

## Create Greeting Domain Object

1. In `Project` tab on the left, expand `src > main > java > edu.midlandu.codeacademy.simplerest`
2. Right-click on package `edu.midlandu.codeacademy.simplerest`
3. `New > Java Class`
4. Enter `Greeting` for `Name`
5. Click `OK`
6. Replace `public class Greeting { }` with the following:

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

## Create Greeting REST Controller

1. Right-click on package `edu.midlandu.codeacademy.simplerest`
2. `New > Java Class`
3. Enter `GreetingController` for `Name`
4. Click `OK`
5. Replace `public class GreetingController { }` with the following:

```java
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
```

## Restart

In the `Run` window at the bottom, find the green restart arrow. It will be on the left. Click it.

## Check It

In your browser, visit <http://localhost:8080/greeting>.

You should see:

```json
{"id":1,"content":"Hello, World!"}
```

Reload the window and the `id` should increment.

Add `?name=User` to the URL. You should see:

```json
{"id":3,"content":"Hello, User!"}
```

## Extra

Add new properties to `Greeting` and see what JSON you get. Try arrays, `java.lang.ArrayList`, `java.util.Date`, etc.
