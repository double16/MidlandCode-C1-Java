package edu.patdouble.simplerest;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(exclude = {"id"})
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id; // null means a guest checkout

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Embedded
    @Getter
    @Setter
    private Address billingAddress;

//    @Embedded
//    @Getter
//    @Setter
//    private Address shippingAddress;
}
