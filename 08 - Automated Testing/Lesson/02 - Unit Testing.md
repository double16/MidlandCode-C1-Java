# Unit Testing

JUnit is the most popular unit testing tool for Java. It uses normal Java classes and annotations.

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Test
    public void contextLoads() {
        // this test ensures Spring loads, so no further work here
    }
}
```
