# Syntax and Compiling

## Classes

All code in Java is contained in a `class`. Classes will be described in more detail later. In short, Java is an object oriented language and forces use of object oriented constructs. A class is defined as:

```java
class MyClass {

}
```

## Methods

Methods are similar to functions, but are part of the class that defines them. Methods are one of two constructs that contains code.

```java
class MyClass {
    public void method1() {
        System.out.println("This is method 1");
    }
    public int method2() {
        System.out.println("This is method 2");
        return 1;
    }
}
```

### main

The entry point to a Java application is the `main` method. This method can be contained in any class.

```java
class MyClass {
    public static void main(String[] args) {
        System.out.println("The application is running.");
        System.exit(0);
    }
}
```

### Compiling

Java is a compiled language. Source code is stored in `.java` files and compiled into `.class` files. `.java` files are not run directly. Usually a more sophisticated build system is used to compile and package the code, but we'll use the Java compiler directly.

```shell
$ javac MyClass.java
$ java MyClass
The application is running.
```

## Comments

There are two styles of comments. Single line comments and multiple line comments. Multiple line comments written in a special form are called `javadoc` comments. They will be included with auto-generated documentation using the `javadoc` tool.

```java
class MyClass {
/*
Multiple line comment.
The slash-splat can start anywhere.
The splat-slash can end anywhere.
*/
    // Single line comment
    public void method1() {
        System.out.println("This is method 1");
    }
    /**
     * The slash-splat-splat starts a javadoc comment.
     * Javadoc comments can be made for classes, methods and fields.
     * @return an integer
     */
    public int method2() {
        System.out.println("This is method 2");
        return 1;
    }
}
```

## Primitives

Java has the following primitive data types. They have the same precision on all platforms.

| Type    | Description                          | Example     |
| ------- | ------------------------------------ | ----------- |
| int     | 32-bit integer                       | 123         |
| long    | 64-bit integer                       | 12345L      |
| short   | 16-bit integer                       | 123         |
| byte    | 8-bit integer                        | 127, 0x55   |
| float   | 32-bit floating point, i.e. decimals | 1.2f        |
| double  | 64-bit floating point                | 1.2         |
| char    | 16-bit Unicode character             | 'a', \u1234 |
| boolean | true or false                        | true, false |

## String

Strings are almost a primitive, but they are indeed objects rather than a finite bit sized value. Strings are defined by using double quotes. The compiler and runtime have special considerations for Strings.

```java
class MyClass {
    public static void main(String[] args) {
        System.out.println("first" + " " + "last");
    }
}
```

## Arrays

Arrays can be made of any primitive or object. They have a fixed size that is set when created. Arrays have bounds protection such that Java prevents indexing an array beyond it's bounds.

```java
class MyClass {
    public static void main(String[] args) {
        int[] integers = new int[20];
        System.out.println("Array size is " + integers.length);
        System.out.println("First element is " + integers[0]);
    }
}
```

## If-Else

Conditionals use `if` and `else`. The conditional expression must resolve to a `boolean` value. Truthy values are not used in Java.

```java
class MyClass {
    public static void main(String[] args) {
        if (2 > 3) {
            System.out.println("Math error");
        } else if (2 != 2) {
            System.out.println("Math error");
        } else {
            System.out.println("Things look ok");
        }
    }
}
```

## Switch

The switch statement allows for conditional on integers and strings (strings for Java 8+ only).

```java
class MyClass {
    public static void main(String[] args) {
        System.out.print("Type an animal to hear it speak: ");
        String animal = System.console().readLine();
        switch (animal) {
            case "dog":
              System.out.println("bark!");
              break;
            case "cat":
              System.out.println("bark!");
              break;
            default:
              System.out.println("I don't know that animal.");
        }
    }
}
```

## Loops

### while

```java
class MyClass {
    public static void main(String[] args) {
        int c = 10;
        while (c > 0) {
            System.out.println(c);
            c--;
        }
    }
}
```

### for

```java
class MyClass {
    public static void main(String[] args) {
        int[] integers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for(int i = 0; i < integers.length; i++) {
            System.out.println(integers[i]);
        }
    }
}
```

### foreach

```java
class MyClass {
    public static void main(String[] args) {
        int[] integers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for(int value : integers) {
            System.out.println(value);
        }
    }
}
```

## Lambdas

Lambdas are inline functions that can be passed as values. They were introduced in Java 8. This is a compile-time feature, we'll look at how these work in detail later.

```java
import java.util.ArrayList;
class MyClass {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        // single line lambda
        integers.forEach(i -> System.out.println(i));
        // multiple line lambda
        integers.forEach(i -> { if (i%2 == 0) System.out.println(i); });
    }
}
```
