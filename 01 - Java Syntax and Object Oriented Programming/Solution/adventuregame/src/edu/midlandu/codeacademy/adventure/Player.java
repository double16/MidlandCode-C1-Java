package edu.midlandu.codeacademy.adventure;

import java.math.BigDecimal;

public class Player {
    private Persona persona;
    private String name;
    private int health;
    private BigDecimal wealth;

    public Player(Persona persona, String name) {
        this.persona = persona;
        this.name = name;
        this.health = persona.health;
        this.wealth = persona.wealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (health <= 0) {
            throw new IllegalStateException(toString()+" is dead :X");
        }
    }

    public BigDecimal getWealth() {
        return wealth;
    }

    public void setWealth(BigDecimal wealth) {
        this.wealth = wealth;
        if (wealth.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException(toString()+" is broke :$");
        }
    }

    public String toString() {
        return name + " the " + persona.name().toLowerCase();
    }

    public String getStatus() {
        return toString() + " is at " + health + "% health and has $"+wealth;
    }
}
