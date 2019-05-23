package edu.patdouble.simplerest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
public class CartController {
    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(path = "/cart", method = RequestMethod.GET)
    public Cart getCart() {
        Cart cart = new Cart();
        cart.setFirstName("John");
        cart.setLastName("Doe");

        CartItem item = new CartItem();
        item.setProduct("apple");
        item.setQuantity(2);
        item.setPrice(BigDecimal.valueOf(1.50));
        cart.getItems().add(item);

        item = new CartItem();
        item.setProduct("orange");
        item.setQuantity(12);
        item.setPrice(BigDecimal.valueOf(10.00));
        cart.getItems().add(item);

        log.info("Returning cart {}", cart);

        return cart;
    }

    @RequestMapping(path = "/cart", method = RequestMethod.POST)
    public long submit(@RequestBody Cart cart) {
        Order order = new Order();
        order.setFirstName(cart.getFirstName());
        order.setLastName(cart.getLastName());
        BigDecimal total = BigDecimal.ZERO;
        for(CartItem i : cart.getItems()) {
            total = total.add(i.getPrice());
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(i.getProduct());
            orderItem.setPrice(i.getPrice());
            order.getItems().add(orderItem);
        }
        order.setTotalPrice(total);
        orderRepository.save(order);
        return order.getId();
    }
}
