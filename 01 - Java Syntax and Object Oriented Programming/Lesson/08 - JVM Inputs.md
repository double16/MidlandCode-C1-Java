# JVM Inputs

The JVM can take input in a variety of ways.

## Command Line Arguments

The command line to `java`, after the name of the class to run, is available in the argument to `main`.

```java
public class MyClass {
    public static void main(String[] args) {
        System.out.println("The arguments are:");
        for(String arg : args) {
            System.out.println(arg);
        }
    }
}
```

## Environment Variables

Environment variables are available to Java applications like many other languages.

```java
public class MyClass {
    public static void main(String[] args) {
        System.out.println("System program path is: "+System.getenv("PATH"));
    }
}
```

## System Properties

System properties are unique to Java. They are specified on the command line using one or more `-D` arguments.

```shell
$ java -Dprop1=a -Dprop2=b MyClass
```

```java
public class MyClass {
    public static void main(String[] args) {
        System.out.println("prop1 = "+System.getProperty("prop1"));
        System.out.println("prop2 = "+System.getProperty("prop2"));
    }
}
```
