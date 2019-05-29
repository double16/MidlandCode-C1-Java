package edu.patdouble.simplerest;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(exclude = {"id"})
@ToString
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @Getter
    @Setter
    private Order order;

    @ManyToOne
    @Getter
    @Setter
    private Product product;

    @Getter
    @Setter
    private int quantity;

    @Embedded
    @Getter
    @Setter
    private Money price;
}
