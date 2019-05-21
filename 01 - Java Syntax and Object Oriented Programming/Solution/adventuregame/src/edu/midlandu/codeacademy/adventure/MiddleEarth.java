package edu.midlandu.codeacademy.adventure;

import java.math.BigDecimal;

public class MiddleEarth extends World {
    @Override
    public String getName() {
        return "Middle-Earth";
    }

    @Override
    public void initChallenges() {
        Challenge orc = new Challenge("You are facing an Orc");
        orc.choices.add(new Choice(Action.FIGHT, "killed the orc", 12, BigDecimal.valueOf(-10)));
        orc.choices.add(new Choice(Action.FLEE, "ran from the orc", 8, BigDecimal.ZERO));
        challenges.add(orc);

        Challenge laketownFerry = new Challenge("You are on the shore and need to reach Laketown. Do you pay for a ferry, or swim?");
        laketownFerry.choices.add(new Choice(Action.PAY, "hired a ferry to Laketown", 4, BigDecimal.valueOf(-20)));
        laketownFerry.choices.add(new Choice(Action.SWIM, "swam to Laketown", 10, BigDecimal.ZERO));
        challenges.add(laketownFerry);
    }
}
