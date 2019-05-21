package edu.midlandu.codeacademy.adventure;

import java.math.BigDecimal;

public class Chicago1920sWorld extends World {
    @Override
    public String getName() {
        return "Chicago 1920s";
    }

    @Override
    public void initChallenges() {
        Challenge thug = new Challenge("You are facing a thug");
        thug.choices.add(new Choice(Action.FIGHT, "beat up the thug", 12, BigDecimal.valueOf(-10)));
        thug.choices.add(new Choice(Action.FLEE, "ran from the thug", 8, BigDecimal.ZERO));
        challenges.add(thug);
    }
}
