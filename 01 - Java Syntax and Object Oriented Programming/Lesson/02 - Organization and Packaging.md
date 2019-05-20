# Organization and Packaging

Java provides a method to package code to logically organize it, provide protection against class name collisions and enforce some access restrictons.

## Packages

Packages are dot separated names. They usually include an owner followed by one or more components ordered from left to right with the right being the most specific. Classes should be placed into packages, even though Java does not require it.

| Package                         | Description                             |
| ------------------------------- | --------------------------------------- |
| java.util                       | Core Java Utilities                     |
| java.net                        | Core Java Network                       |
| org.apache.commons.lang3        | Apache Commons value add to `java.lang` |
| org.apache.commons.collections4 | Apache Commons collections              |

## Source Folder Structure

Packages dictate a folder structure. Each dot indicates a new folder named with the package. So `org.apache.commons.lang3` becomes folder `org/apache/commons/lang3`. Classes in that package will be in the `lang3` folder.

```java
package edu.patdouble;
public class MyClass {
}
```

This class must exist in the file `edu/patdouble/MyClass.java`. The full name of the class is `edu.patdouble.MyClass`.

## Imports

Classes in a given package are visible only to others in the same package. To use classes in other packages they must be imported.

```java
package edu.patdouble;
import java.util.ArrayList;
public class MyClass {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
    }
}
```

Imports can use a wildcard to import all classes in a package.

```java
package edu.patdouble;
import java.util.*;
public class MyClass {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
    }
}
```

However, the wildcard does not extend to sub-packages. Importing `java.*` will NOT import `java.util.*`.

## Class Path

The "class path" is a list of directories (and files as we'll see later) that contain the packages and classes that are usable. Each entry in the class path points to the root of where packages start. Typically, each application instance has it's own class path rather than a system wide class path.

The class path is configured when running the `java` command. The JDK classes themselves are in a "boot classpath" and set by the `java` command, there is no need to reference them.

```shell
$ java -cp .:/home/myuser/classes edu.patdouble.MyClass
The application is running.
```

## Java Development Kit (JDK) Documentation

You can find documentation on available JDK packages and classes at [Oracle](https://docs.oracle.com/javase/10/docs/api/index.html?overview-summary.html).
