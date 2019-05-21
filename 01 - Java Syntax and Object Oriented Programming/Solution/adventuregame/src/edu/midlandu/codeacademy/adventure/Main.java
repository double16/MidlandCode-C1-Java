package edu.midlandu.codeacademy.adventure;


import java.io.Console;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Console console = System.console();
        String playerName = System.getenv("USER");

        World world = new MiddleEarth();
        //World world = new Chicago1920sWorld();
        world.initChallenges();

        console.format("Welcome to %s!\n", world.getName());

        console.format("Who would you like to be, "+playerName+", "+ Arrays.asList(Persona.values())+" ? ");
        Persona persona = null;
        while (persona == null) {
            console.flush();
            try {
                persona = Persona.valueOf(console.readLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                console.writer().println("You must choose from: "+Arrays.asList(Persona.values()));
            }
        }

        Player player = new Player(persona, playerName);
        Story story = new Story(world, player);

        for(Challenge challenge : world.challenges) {
            Choice choice = null;
            while (choice == null) {
                try {
                    console.format("\n%s\n%s\n", player.getStatus(), challenge.description);
                    for(Choice ch : challenge.choices) {
                        console.writer().print(ch.toString());
                        console.writer().print(", ");
                    }
                    console.writer().print(" ? ");

                    console.flush();
                    Action action = Action.valueOf(console.readLine().toUpperCase());
                    for(Choice ch : challenge.choices) {
                        if (ch.getAction() == action) {
                            choice = ch;
                        }
                    }
                    if (choice == null) {
                        console.format("You can't do that here!\n");
                    }
                } catch (IllegalArgumentException e) {
                    console.format("You can't do that here!\n");
                }
            }

            try {
                story.addStoryLine(choice.apply(player));
            } catch (IllegalStateException e) {
                // this means the adventure is over
                story.addStoryLine(e.getMessage());
            }
        }

        console.format("\nThe adventure is done. Here is your story.\n\n");

        for(String s : story.story) {
            console.writer().println(s);
        }
    }
}
