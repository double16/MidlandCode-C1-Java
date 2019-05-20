# Threads

Java allows multiple threads of execution, a.k.a. concurrency, unlike JavaScript. Concurrency is a difficult and advanced topic. Fortunately, many libraries and web servers, such as Tomcat, do their best to hide this from the developer.

In particular, avoiding `static` fields will go a long way to avoid concurrency problems.

Java uses the `java.lang.Thread` class to run a thread of execution. It is simple to create a run a thread using this class.

```java
public class MyClass {
    public static void main(String[] args) {
        Thread t = new Thread(() -> System.out.println("new thread"));
        t.start();
    }
}
```
