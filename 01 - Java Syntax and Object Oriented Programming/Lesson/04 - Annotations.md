# Annotations

Annotations provide ways to add metadata to classes, methods and fields. The metadata may be informational only, validation code or allow code generation. Multiple annotations can be applied to the same class or member.

## Compile Hints

In this example we've marked the `oldopen()` method as deprecated. This annotation is processed at compile time and will emit a warning if `oldopen()` is called. The compiled code will not execute differently.

```java
public class Door {
    private int height, width;
    @Deprecated
    public void oldopen() {
        System.out.println("opening the old way");
    }
    public void open() {
        System.out.println("open");
    }
    public void close() {
        System.out.println("close");
    }
}
```

## Validation

The validation API is one example of annotations that are processed during runtime. Instead of writing validation code in every method, an annotation can be added and a library will perform the validation.

```java
import javax.validation.NotNull;
public class Door {
    String location
    public void setLocation(@NotNull location) {
        System.out.println("setting location to "+location);
        this.location = location;
    }
}
```
