# Collections

Arrays in Java can be difficult to work with. They have a fixed size and limited methods. The Java collection classes make using data types such as lists, maps and sets much easier to use.

## Collection

The base class of most collections is `java.util.Collection`. There are basic operations defined that all collections have. Generally it's best to declare variabels using this interface.

## List

A `java.util.List` is similar to an array in that it's elements maintain order. Lists can be added to, removed from, sorted, etc.

Popular implementations of `List` are:

```java
public class MyClass {
    public static void main(String[] args) {
        List arrayList = new ArrayList();
        List linkedList = new LinkedList();
    }
}
```

## Set

The `java.util.Set` holds an unique set of elements. Order is defined differently by the implementation. Some have an undefined order, some maintained a sorted order, ...

Popular implementations of `Set` are:

```java
public class MyClass {
    public static void main(String[] args) {
        Set hashSet = new HashSet(); // requires hashCode()! see below
        Set treeSet = new TreeSet(); // sorted, requires Comparable! see below
    }
}
```

## Map

The `java.util.Map` holds key-value pairs. The key and value may be of any class. However, the key must implement equality and in some cases `java.lang.Comparable`.

Popular implementations of `Map` are:

```java
public class MyClass {
    public static void main(String[] args) {
        Map hashMap = new HashMap(); // requires hashCode()! see below
        Map treeMap = new TreeMap(); // sorted keys, requires Comparable! see below
    }
}
```

## Equality and Comparisons

Discussing collections is a good place to discuss equality of Java objects. Many classes in Java depend on an object's ability to determine if it is equal to another object. Some classes also depend on an object being able to determine it's sort order with respect to another object.

There are three methods a class must implement for this capability.

### equals() and hashCode()

Java does not provide a built-in way to determine if two different objects are equal to each other. Two methods are needed, the `equals()` and `hashCode()` methods.

```java
import java.util.Objects;

public class Door {
    int height, width;
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Door)) {
            return false;
        }
        Door d = (Door) obj;
        return height == d.height && width == d.width;
    }
    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }
}
```

Equality can be defined for any type, so there needs to be a check for compatible types. Then the fields that should contribute to equality need to be checked.

The hash code is an integer that must be equal if two objects are equal, and ideally should not be equal if the objects aren't equal. It's generally important for key objects in a `java.util.HashMap` but is commonly used by other classes.

### Comparable

For sorting algorithms, two objects need to determine how they relate in sort order. The `compareTo()` method must return 0 if the objects are equal, <0 if `this` is less than the argument and >0 if `this` is greater than the argument.

```java
public class Door implements Comparable {
    int height, width;
    public int compareTo(Object obj) {
        // For simplicity, assume we're always working with doors
        Door d = (Door) obj;
        return (height*width) - (d.height*d.width);
    }
}
```

## Iterating

Iterating over collections, lists and sets is straight forward.

```java
public class MyClass {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for(String s : list) {
            System.out.println(s);
        }

        Set<String> set = new HashSet<>();
        for(String s : set) {
            System.out.println(s);
        }
    }
}
```

Maps have key-value pairs, so iterating is different.

```java
public class MyClass {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "one");
        map.put("2", "two");

        for(Map.Entry<String, String> e : map) {
            System.out.println(e.getKey() + "=" + e.getValue());
        }
    }
}
```
