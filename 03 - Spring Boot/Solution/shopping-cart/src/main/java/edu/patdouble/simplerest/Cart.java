package edu.patdouble.simplerest;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private String firstName;
    private String lastName;
    private List<CartItem> items = new ArrayList<>();
}
