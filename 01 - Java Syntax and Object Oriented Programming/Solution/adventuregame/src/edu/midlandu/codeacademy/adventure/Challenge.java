package edu.midlandu.codeacademy.adventure;

import java.util.ArrayList;
import java.util.List;

public class Challenge {
    String description;
    List<Choice> choices = new ArrayList<>();

    public Challenge(String description) {
        this.description = description;
    }

    public void choose(Player player, Choice choice) {
        System.console().writer().println(choice.getResult());
        choice.apply(player);
    }
}
