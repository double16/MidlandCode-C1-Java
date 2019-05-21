package edu.midlandu.codeacademy.adventure;

import java.math.BigDecimal;
import java.util.List;

public class MiddleEarth extends World {
    private static final Challenge ORC = new Challenge("You are facing an Orc", List.of(
            new Choice(Action.FIGHT, "killed the orc", 12, BigDecimal.valueOf(-10)),
            new Choice(Action.FLEE, "ran from the orc", 8, BigDecimal.ZERO)
    ));

    @Override
    public String getName() {
        return "Middle-Earth";
    }

    @Override
    public void initChallenges() {
        challenges.add(ORC);

        Challenge laketownFerry = new Challenge("You are on the shore and need to reach Laketown. Do you pay for a ferry, or swim?");
        laketownFerry.choices.add(new Choice(Action.PAY, "hired a ferry to Laketown", -4, BigDecimal.valueOf(-20)));
        laketownFerry.choices.add(new Choice(Action.SWIM, "swam to Laketown", 10, BigDecimal.ZERO));
        challenges.add(laketownFerry);

        challenges.add(ORC);
    }
}
