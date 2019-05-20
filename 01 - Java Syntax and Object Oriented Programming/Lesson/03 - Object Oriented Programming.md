# Object Oriented Programming

Java is designed around object oriented programming (OOP). In this section we'll discuss object oriented programming and specifically how Java implements it.

## Class and Object

The primary concept in OOP is the class. A class provides a template of properties and operations that one or more objects have in common. An object is an instance of a class. In Java, an object is an instance of exactly one class. The idea is to encapsulate the data and operations of a logical component of an application into one place, the class. The data specified by a class, also called fields or properties, is separate for each object.

The following example shows how we might model a door using a class:

```java
public abstract class Door {
    int height, width;
    public void open() {
        System.out.println("open");
    }
    public void close() {
        System.out.println("close");
    }
    public abstract void lock();
    public abstract void unlock();
}
```

We've defined some physical properties of the door using `height` and `width`. We've also defined common operations on a door using methods. The `abstract` keyword allows us to define the method but without implementation, the reason we'll describe later.

## Class Hierarchies

Classes may extend other classes, which means a class starts with another class and modifies it's data or operations. We'll use our model of a door to illustrate how this is helpful.

```java
public class OutsideHouseDoor extends Door {
    public void lock() {
        System.out.println("lock the house");
    }
    public void unlock() {
        System.out.println("unlock the house");
    }
}

public class OverheadGarageDoor extends Door {
    public void open() {
        System.out.println("lift the overhead door");
    }
    public void close() {
        System.out.println("lower the overhead door");
    }
    public void lock() {
        System.out.println("lock the garage");
    }
    public void unlock() {
        System.out.println("unlock the garage");
    }
}
```

The `extends` keyword is restricted to a single class to keep things simple. A discussion of the issues with extending multiple classes is beyond our scope.

## Inheritance

Inheritance means that when class B extends another class A, class B gets all of the properties and behavior (operations) of class A. Objects of class B behave as class A.

In the door example, `OutsideHouseDoor` inherits the `open` and `close` methods of `Door`.

## Polymorphism

Polymorphism means that when class B extends another class A and re-defines a method, the class B method implementation is chosen over class A. Consider this:

```java
public class MyClass {
    public static void main(String[] args) {
        Door door = new OverheadGarageDoor();
        door.open();
    }
}
```

What will `door.open()` do? `door` is declared as type `Door`, yet it was assigned an object of `OverheadGarageDoor`. Every object knows which class it is. It's implicitly part of the data in the object. So when `open()` is called, it calls the `OverheadGarageDoor` method and not the `Door` method.

You can get the class of an object using the `getClass()` method:

```java
public class MyClass {
    public static void main(String[] args) {
        Door door = new OverheadGarageDoor();
        System.out.println("The door is a "+door.getClass());
    }
}
```

Why do this? Any code that uses a `Door` object can use any subclass or implementaton of `Door` without knowledge of the subclasses. Modification of subclass code does not require code using the door to be modified. The changes are encapsulated in the class.

## Abstract Methods

We saw the keyword `abstract` applied to the `lock()` and `unlock()` methods in `Door`. This allows a class to define a method that must exist in subclasses when the defining class does not know the implementation. The `Door` class is called an abstract class. It cannot be instantiated (i.e. using the `new` keyword). Classes that can be instantiated are called concrete classes.

## Interfaces

An interface is used to define a behavior contract. It lists methods that implementing classes must have but gives no implementation itself. It is similar to abstract classes but does not have the restriction that only one interface can be implemented. This is because the interface only defines methods, or behavior, but not data.

```java
public interface Lockable {
    void lock();
    void unlock();
}
public abstract class Door implements Lockable {
    int height, width;
    public void open() {
        System.out.println("open");
    }
    public void close() {
        System.out.println("close");
    }
}
```

The `Door` class will still have `abstract` `lock()` and `unlock()` methods. The advantage is other classes in the application can implement `Lockable` such as cars or treasure chests. Any code that deals with locks can reference the `Lockable` interface instead of `Door`.

```java
public class MyClass {
    public static void main(String[] args) {
        Lockable lockable = new OverheadGarageDoor();
        System.out.println("The lockable is a "+lockable.getClass());
        lockable = new Delorian();
        System.out.println("The lockable is a "+lockable.getClass());
    }
}
```

## Access Protection

Some methods and fields are intended to only be used by the class. Java has access modifiers to protected class members from unauthorized access.

```java
public abstract class Door {
    private int height, width;
    public Door(int height, int width) {
        this.height = height;
        this.width = width;
    }
    private void onlyThisClass() { }
    protected void meAndMySubclasses() { }
    void classesInMyPackage() { }
    public void open() { }
    public void close() { }
}
```

| Keyword   | Access                                                   |
| --------- | -------------------------------------------------------- |
| public    | Access from any code                                     |
| protected | Access from the class and any subclasses                 |
|           | No modifier uses the default, any classes in the package |
| private   | Only access from the current class                       |

## Properties vs. Fields

It is best practice to hide data behind methods, another aspect of encapsulation. Allowing outside code to change data can lead to inconsistent state and fragile systems.

```java
public class Door {
    int height, width;
    public Door(int height, int width) {
        this.height = height;
        this.width = width;
    }
}
```

```java
public class Door {
    private int height, width;
    public Door(int height, int width) {
        this.height = height;
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
}
```

## Constructors

Constructors are special methods called when an object is instantiated. There can be multiple if they have different argument types.

```java
public class Door {
    private int height, width;
    public Door() {
        this.height = 0;
        this.width = 0;
    }
    public Door(int height, int width) {
        this.height = height;
        this.width = width;
    }
    public static void main(String[] args) {
        Door d1 = new Door();
        Door d2 = new Door(50, 30);
    }
}
```

## `static`

The `static` keyword is used to mark fields or methods as not being associated with an object. Static members must always be part of a class, but aren't part of the object. We've already see the static `main` method. Fields can be static, although this should be used with caution. The most common use of static fields is for constants, and to make sure they don't change the `final` modifier is used.

```java
public class Door {
    public static int DOOR_COUNT = 0;
    public static final String DOOR_LABEL = "Door";
    private int height, width;
    public Door() {
        this.height = 0;
        this.width = 0;
        DOOR_COUNT++;
    }
    public Door(int height, int width) {
        this.height = height;
        this.width = width;
    }
    public static void main(String[] args) {
        Door d1 = new Door();
        Door d2 = new Door(50, 30);
    }
}
```

## `this`

The `this` keyword is used to reference the current object. It is used to resolve confusion when variable names could be arguments or object fields. This has been used in the `Door` constructor to initialize fields.

## Type Casting

Sometimes you have a base class reference, like `Lockable` but somehow know it's also a `Door`. How do you access the `Door` methods? You can cast the `Lockable` to a `Door`. Note that this should be avoided if possible and will cause an error at runtime if the casted object is not of the desired type.

```java
public class MyClass {
    public static void main(String[] args) {
        Lockable lock = new OverheadGarageDoor();
        lock.lock();
        Door door = (Door) lock;
        door.open();
    }
}
```

## Enums

The `enum` keyword defines something like a class that can have a fixed set of values.

```java
public enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY
}
public class MyClass {
    public static void main(String[] args) {
        Day d1 = Day.SUNDAY;
        Day d2 = Day.MONDAY;
    }
}

import static Day.*;
public class MyClass2 {
    public static void main(String[] args) {
        Day d1 = SUNDAY;
        Day d2 = MONDAY;
    }
}
```

Enums are special types of classes, so they can have extra methods and data defined.
