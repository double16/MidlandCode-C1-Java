# Shopping Cart "Sprint 2"

Sprint 2 will write the cart into the database using JPA entities.

Typically the shopping cart and order models are different. In our case we don't persist the cart on the server side, only the completed order.

Create Java classes for at least `Order` and `OrderItem`. They will be linked together with a `@OneToMany` JPA annotation.

In `OrderItem` you can reference your product objects using `@OneToMany` or even copy properties into `OrderItem`. In the wild, product descriptions and prices change and you don't want your old orders to change. So copying properties is a viable approach.

Update your controller's POST method to return the order object.

Update the UI to display the returned order.

It is useful to know that the Repository classes you've made to create REST endpoints can also be used in your Java classes, such as `@RestController`, to manage entities using Java code.

```java
// OrderRepository.java
@RepositoryRestResource
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

}
```

```java
// CartController.java
@RestController
public class CartController {
    @Autowired
    OrderRepository orders;

    @RequestMapping(path = "/api/cart", method = RequestMethod.POST)
    public Order submit(@RequestBody Cart cart) {
        Order order = new Order();
        // ...
        orders.save(order); // save is from OrderRepository, which inherits it from CrudRepository
        return order;
    }
}
```

See [`CrudRepository`](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html) for available methods.

## Use `curl` to test

You can submit a cart without having Angular done.

1. Visit <http://localhost:8080/api/cart>
2. Save the JSON to a file like `cart.json`
3. Edit the JSON with any text editor
4. Open a terminal in the directory with your `cart.json`. (If you save the file into your IntelliJ project directory, you can use IntelliJ's Terminal)
5. `curl -X POST -d @cart.json -H "Content-Type: application/json" http://localhost:8080/api/cart`

You can repeat 3 and 5 to create new orders.
