# JPA

JPA (Java Persistence API) is a method for storing Java objects into relational databases. It follows a pattern known as Object Relational Mapping (ORM). It greatly simplifies persistence. The down side is less visibility and control in the underlying database.

We'll cover some of the API. An complete discussion is out of scope. [Introduction to the Java Persistence API](https://docs.oracle.com/javaee/6/tutorial/doc/bnbpz.html) is a recommended resource.

An object that can be stored in a database is called an `Entity`. `@Id` marks the field as the primary key of the object. `@GeneratedValue` determines how the ID will be generated. In this case, the database will automatically generate a key.

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
}
```

## Hibernate

JPA is only an API, basically a set of annotations. It needs an implemention to make it work. Hibernate is the most popular implementation and works with a variety of databases.

## Relationships

Relationships between entities can be maintained by JPA. These are kept in `List` or `Set` objects. There are `@OneToMany`, `@ManyToMany` and `@ManyToOne` relationships. `@OneToMany` is the most common.

```java
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private List<Pet> pets;

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

    @OneToMany
    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
```
