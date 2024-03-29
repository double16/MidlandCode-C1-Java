# Unit Testing

JUnit is the most popular unit testing tool for Java. It uses normal Java classes and annotations.

The following is a simple test of Spring Boot initialization provided by the generated project:

```java
package edu.<your_name>.simplerest;

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

Here is a test for the `Money` class in the solution directory:

```java
package edu.<your_name>.simplerest;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MoneyTest {
    @Test
    public void testConstructor() {
        Money m = new Money(BigDecimal.valueOf(10.00), "USD");
        assertEquals(BigDecimal.valueOf(10.00), m.getAmount());
        assertEquals("USD", m.getCurrency());
    }

    @Test
    public void testDefaultConstructor() {
        Money m = new Money();
        assertNull(m.getAmount());
        assertNull(m.getCurrency());
    }

    @Test
    public void testEquals() {
        Money m10a = new Money(BigDecimal.valueOf(10.00), "USD");
        Money m10b = new Money(BigDecimal.valueOf(10.00), "USD");
        Money m5a = new Money(BigDecimal.valueOf(5.00), "USD");
        Money m5b = new Money(BigDecimal.valueOf(5.00), "USD");

        assertEquals(m10a, m10a);
        assertEquals(m10a, m10b);
        assertNotEquals(m10a, m5a);
    }
}
```
