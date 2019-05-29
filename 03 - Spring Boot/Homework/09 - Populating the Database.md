# Populating the Database

One of the issues when testing, or even initializing a new install of your product, is populating the database. Usually when starting the project you use a database tool to manually enter data. Once you've gotten a bit down the road, you won't want to do that. It's difficult to share that with your team and there will be a reason to delete your database and start over.

You want to commit your initial data into source control with your project. There are a few ways to do this. We'll look at using JPA and Spring to get this done.

Spring supports a `CommandLineRunner` bean which will be run each time the application starts. We can create a method that creates the objects we want in the database and save them. We'll want to handle the case of the database already being populated so we don't duplicate.

## Creating new Objects

The first attempt we will do is create the objects. We'll end up with duplicates each time we run, but we'll fix that later. Change the `new Product(...)` portion to the object you want to insert. This example assumes there is a constructor that takes the fields of the product.

```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
  @Autowired
  ProductRepository repository;

  @Bean
  CommandLineRunner initDatabase() {
    return args -> {
      Product p;
      p = new Product("SKU-1234", "Dog Whistle", BigDecimal.valueOf(10.00));
      repository.save(p);

      p = new Product("SKU-1235", "Cat Collar", BigDecimal.valueOf(8.00));
      repository.save(p);
    };
  }
}
```

## Checking for Existance

Ideally we could run our application and only add objects that are missing. We'll add a method to do that to keep from duplicating code.

```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
  @Autowired
  ProductRepository repository;

  Product saveProductIfMissing(String sku, String name, BigDecimal price) {
    if (repository.findBySku(sku).isEmpty()) {
      return repository.save(new Product(sku, name, price));
    }
    return null; // indicates we didn't create anything
  }

  @Bean
  CommandLineRunner initDatabase() {
    return args -> {
      saveProductIfMissing("SKU-1234", "Dog Whistle", BigDecimal.valueOf(10.00));
      saveProductIfMissing("SKU-1235", "Cat Collar", BigDecimal.valueOf(8.00));
    };
  }
}
```
