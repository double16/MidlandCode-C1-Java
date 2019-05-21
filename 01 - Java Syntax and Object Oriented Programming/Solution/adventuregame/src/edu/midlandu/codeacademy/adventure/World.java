package edu.midlandu.codeacademy.adventure;

import java.util.ArrayList;
import java.util.List;

public abstract class World {
    List<Challenge> challenges = new ArrayList<>();

    public abstract String getName();
    public abstract void initChallenges();
}
