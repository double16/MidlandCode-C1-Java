package edu.patdouble.simplerest;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItem {
    private String product;
    private int quantity;
    private BigDecimal price;
}
