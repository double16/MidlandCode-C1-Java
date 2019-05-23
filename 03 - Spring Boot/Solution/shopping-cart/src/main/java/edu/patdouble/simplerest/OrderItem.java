package edu.patdouble.simplerest;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@EqualsAndHashCode(exclude = {"id"})
@ToString
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @ManyToOne
    @NotNull
    @Getter
    @Setter
    private Order order;

    @Getter
    @Setter
    private String product;

    @Getter
    @Setter
    private BigDecimal price;
}
