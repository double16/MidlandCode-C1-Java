# Generics

A good example of general purpose classes are the collections classes. They are intended to contain any object. How do we ensure type safety in such cases? Until JDK 5 there was not a way for the compiler to do so.

JDK 5 introduced "generics". It is a way to specify allowed classes for these generic use cases. Generics use `< >` brackets to specify the desired type.

```java
public class MyClass {
    public static void main(String[] args) {
        List anything = new ArrayList();
        anything.add("abcdef");
        anything.add(BigDecimal.valueOf(1234));

        List<String> stringsOnly = new ArrayList<String>();
        // or as a shortcut: List<String> stringsOnly = new ArrayList<>();
        stringsOnly.add("abcdef");
        // the following will not compile
        stringsOnly.add(BigDecimal.valueOf(1234));
    }
}
```

A class may specify multiple generic classes:

```java
public class MyClass {
    public static void main(String[] args) {
        Map<String, BigDecimal> map = new HashMap<>();
    }
}
```
