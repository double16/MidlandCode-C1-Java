package edu.midlandu.codeacademy.adventure;

import java.math.BigDecimal;

public class Choice {
    private Action action;
    private String result;
    private int healthPenalty;
    private BigDecimal wealthPenalty;

    public Choice(Action action, String result, int healthPenalty, BigDecimal wealthPenalty) {
        this.action = action;
        this.result = result;
        this.healthPenalty = healthPenalty;
        this.wealthPenalty = wealthPenalty;
    }

    public Action getAction() {
        return action;
    }

    public String getResult() {
        return result;
    }

    /**
     * Apply the choice made to the player. This will adjust the player's health, wealth, etc.
     *
     * @param player the player.
     * @return the story line for the choice.
     */
    public String apply(Player player) {
        player.setHealth(player.getHealth() - healthPenalty);
        player.setWealth(player.getWealth().subtract(wealthPenalty));
        return player.toString() + " choose to " + action.name().toLowerCase() + " and " + result;
    }

    @Override
    public String toString() {
        return action.name().toLowerCase();
    }
}
