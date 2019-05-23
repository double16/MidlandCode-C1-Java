# Lombok

[Project Lombok](https://objectcomputing.com/resources/publications/sett/january-2010-reducing-boilerplate-code-with-project-lombok) seeks to reduce boiler-plate Java code such as getters, setters, equals, hashCode, etc. It provides annotations that the build system will process to generate the necessary code in the `.class` files.

The build system and your IDE need to support annotation processing for Lombok to be effective. The linked documentation shows how to configure both Gradle and Maven to support Lombok when building.

Gradle:

```groovy
dependencies {
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
}
```

Maven:

```xml
<dependencies>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>0.9.2</version>
    </dependency>
</dependencies>
<repositories>
    <repository>
        <id>projectlombok.org</id>
        <url>http://projectlombok.org/mavenrepo</url>
    </repository>
</repositories>
```

For example:

```java
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) &&
                lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
```

... can be written as ...

```java
@Entity
@EqualsAndHashCode(exclude = {"id"})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    @Setter
     private String firstName;

    @Getter
    @Setter
    private String lastName;
}
```

## @Getter and @Setter

Getters and setters are a large source of bloat in Java classes. You can use these annotations together or individually. If you've already written a getter or setter, the annotation will not create a new one or replace it. So it's safe to put these on all the desired fields and later add a setter with business logic.

## @EqualsAndHashCode

`@EqualsAndHashCode` makes implementing these methods much easier. There is a parameter to exclude certain fields, such as `id` or to list the fields to include using `of`.

```java
@EqualsAndHashCode(exclude={"id"})
public class Person {
    //...
}
```

```java
@EqualsAndHashCode(of={"firstName","lastName"})
public class Person {
    //...
}
```

## @ToString

The `toString()` method is normally used for debugging when printing something about the object to a log. Larger objects can be tedious to write `toString()` for. Adding `@ToString` to the class will add all fields to `toString()`.

```java
@ToString
public class Person {
    //...
}
```

## @Data

Many times you want all of the above annotations on a Java class. `@Data` makes this easier. The drawback for things like JPA entities is that you normally want to exclude the `id` field and there isn't a way in `@Data` to do that.

```java
@Data
public class DataObject {
    private String property1;
    private String property2;
}
```

... instead of ...

```java
@EqualsAndHashCode
@ToString
public class DataObject {
    @Getter
    @Setter
    private String property1;

    @Getter
    @Setter
    private String property2;
}
```

## Others

There are a few other interesting annotations you can [read about](https://objectcomputing.com/resources/publications/sett/january-2010-reducing-boilerplate-code-with-project-lombok).
