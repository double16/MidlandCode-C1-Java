# Shopping Cart "Sprint 1"

Now that we have a product database and catalog we want to create a shopping cart.

For this sprint we'll focus on the UI and the JSON format for posting back to the server. (Note that this may or may not be a good pattern for a real world cart).

## Client Side

The object model will be similar to:

* A `Cart` object to hold everything to do with a single user's cart.
* A list of objects in `Cart` to hold the product(s) and quanities.
* Pricing should be left to the server side, otherwise a user can pick their own price (see that done in the wild ;)

The state will be kept in the Angular application. For a better user experience you could store it in LocalStorage. The app won't contact the server with the cart until the user submits the order. The server will receive JSON that is the entire order.

## Server Side

Spring will expect a JSON format that matches classes. It's possible to construct Java objects to reflect about any JSON format. For this exercise we'll let Spring tell us the format to use.

Create Java classes to model your cart. Use a `List` in your cart to holds the list of products. These classes will NOT be entities, i.e. they will not have an `@Entity` annotation. So you can use the `@Data` Lombok annotation here.

Create a `@RestController` (not `@RepositoryRestController`) to receive the POST for the cart. We'll also add a method to return a cart so that we can determine what JSON Spring wants. Follow the `GreetingController` example for the controller since it is using classes that aren't JPA entities.

You will have two methods in your controller, one for GET and one for POST. For now, use `System.out.println` to output the objects you get in POST. Use `@Data` or `@ToString` will help.

The POST result should be a Java class that gives at least an order ID and total price. You can make up an order ID for now, we'll make a real one later.
