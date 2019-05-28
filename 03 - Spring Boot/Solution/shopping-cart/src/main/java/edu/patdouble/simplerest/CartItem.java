package edu.patdouble.simplerest;

import lombok.Data;

@Data
public class CartItem {
    private String sku;
    private String productName;
    private int quantity;
    private Money price;
}
