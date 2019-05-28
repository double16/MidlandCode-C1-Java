package edu.patdouble.simplerest;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EqualsAndHashCode
@ToString
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Getter
    @Setter
    private Date orderDate;

    @ManyToOne
    @Getter
    @Setter
    private Customer customer;

    @Embedded
    @Getter
    @Setter
    private Address billingAddress = new Address();

//    @Embedded
//    @Getter
//    @Setter
//    private Address shippingAddress = new Address();

    @OneToMany(mappedBy = "order")
    @Getter
    @Setter
    private List<OrderItem> items = new ArrayList<>();
}
