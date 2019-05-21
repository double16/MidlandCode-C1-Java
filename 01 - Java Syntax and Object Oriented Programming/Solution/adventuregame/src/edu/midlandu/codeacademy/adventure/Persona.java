package edu.midlandu.codeacademy.adventure;

import java.math.BigDecimal;

/**
 * Improvments:
 *
 * Personas could have different results for actions. For example, a thief could be better at fleeing than a warrior.
 * A warrior would take less damage during fighting. The thief could find more wealth on enemies after they are defeated.
 *
 */
public enum Persona {
    WARRIOR(100, BigDecimal.valueOf(50)),
    THIEF(60, BigDecimal.valueOf(100));

    int health;
    BigDecimal wealth;

    Persona(int health, BigDecimal wealth) {
        this.health = health;
        this.wealth = wealth;
    }
}
