package edu.midlandu.codeacademy.adventure;

import java.util.ArrayList;
import java.util.List;

public class Story {
    World world;
    Player player;
    List<String> story = new ArrayList<>();

    public Story(World world, Player player) {
        this.world = world;
        this.player = player;
    }

    public void addStoryLine(String s) {
        story.add(s);
    }
}
