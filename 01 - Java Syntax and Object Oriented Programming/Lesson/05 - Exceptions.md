# Exceptions

Exception handling is the way Java reports and handles errors. When an error occurs an *exception* is *thrown*. This causes execution to stop and move up the method call stack until a handler for the exception is found.

File I/O is a common example of this. The `try-catch` construct is used to define exception handling.

```java
import java.io.File;
import java.io.IOException;
public class MyClass {
    public static void main(String[] args) {
        File f = new File('test.txt');
        try {
            // we're opening the file then immediately closing it
            f.getInputStream().close();
        } catch (IOException e) {
            // If an I/O error occurs, such as the file doesn't exist, we'll get here
            e.printStackTrace();
        } finally {
          System.out.println("I'm done.");
        }
    }
}
```

Exceptions are objects and have classes. There is a large class hierarchy designed to allow you to handle exceptions at the granularity you wish. Explore the hierarchy using [javadoc](https://docs.oracle.com/javase/10/docs/api/java/lang/Exception.html).

You can throw your own exceptions to report errors.

```java
import java.io.File;
public class MyClass {
    public static void main(String[] args) {
        if (args.length > 0) {
            throw new IllegalArgumentException("too many arguments");
        }
    }
}
```

## Checked and Unchecked Exceptions

Java divides exception into checked and unchecked. Checked exceptions must be handled by an application using a `try-catch` or declared as something that may be thrown. Only exceptions that extend the [`RuntimeException`](https://docs.oracle.com/javase/10/docs/api/index.html?overview-summary.html) are considered unchecked.

```java
import java.io.File;
import java.io.IOException;
public class MyClass {
    public static void main(String[] args) throws IOException {
        File f = new File('test.txt');
        f.getInputStream().close();
    }
}
```
