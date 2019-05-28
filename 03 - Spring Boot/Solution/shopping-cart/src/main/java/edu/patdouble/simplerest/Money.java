package edu.patdouble.simplerest;

import lombok.Data;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@Embeddable
public class Money {
    private BigDecimal amount;
    private String currency;
}
