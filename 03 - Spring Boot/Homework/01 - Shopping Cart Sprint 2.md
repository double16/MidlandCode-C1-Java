# Shopping Cart "Sprint 2"

Sprint 2 will write the cart into the database using JPA entities.

Typically the shopping cart and order models are different. In our case we don't persist the cart on the server side, only the completed order.

Create Java classes for at least `Order` and `OrderItem`. They will be linked together with a `@OneToMany` JPA annotation.

In `OrderItem` you can reference your product objects using `@OneToMany` or even copy properties into `OrderItem`. In the wild, product descriptions and prices change and you don't want your old orders to change. So copying properties is a viable approach.

Update your controller's POST method to return the order object.

Update the UI to display the returned order.
