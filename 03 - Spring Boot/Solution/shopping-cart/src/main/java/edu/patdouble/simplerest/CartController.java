package edu.patdouble.simplerest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ProductRepository productRepository;

    @RequestMapping(path = "/api/cart", method = RequestMethod.GET)
    public Cart getCart() {
        Cart cart = new Cart();
        cart.getCustomer().setFirstName("Frodo");
        cart.getCustomer().setLastName("Baggins");

        CartItem item1 = new CartItem();
        item1.setSku("SKU-1234");
        item1.setQuantity(2);
        item1.setProductName("Dog Whistle");
        cart.getItems().add(item1);

        return cart;
    }

    @RequestMapping(path = "/api/cart", method = RequestMethod.POST)
    public String placeOrder(@RequestBody Cart cart) {
        Order order = new Order();
        order.setOrderDate(new Date());

        if (cart.getCustomer().getId() != null) {
            order.setCustomer(customerRepository.findById(cart.getCustomer().getId()).get());
        } else {
            order.setCustomer(cart.getCustomer());
            customerRepository.save(order.getCustomer());
        }

        order.setBillingAddress(cart.getBillingAddress());
        orderRepository.save(order);

        for(CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);

            List<Product> products = productRepository.findBySku(cartItem.getSku());
            if (products.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "E-PRICECHANGE No product for sku "+cartItem.getSku());
            }
            orderItem.setProduct(products.get(0));

            orderItem.setPrice(orderItem.getProduct().getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItemRepository.save(orderItem);
            order.getItems().add(orderItem);
        }

        return Long.toString(order.getId());
    }
}
