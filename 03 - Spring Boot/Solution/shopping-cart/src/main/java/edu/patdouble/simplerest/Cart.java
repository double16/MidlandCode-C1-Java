package edu.patdouble.simplerest;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private Customer customer = new Customer();
    private Address billingAddress = new Address();
    private Address shippingAddress = new Address();
    private List<CartItem> items = new ArrayList<>();
}
