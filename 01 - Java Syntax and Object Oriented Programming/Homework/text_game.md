# Homework

You will be creating a text based game with at least 5 steps in the adventure.

Specs:

* Have the player enter a name or get it from an environment variable ("USER") or system property ("user.nmae")
* Have the player pick a class to be. The options are "Warrior" or "Thief" (Or more if you prefer).
* From here, for every prompt, refer to the player as `"{Name} the {class}"`.
* Set up 5 challenges to overcome. For example:
    `You come across a river. Would you like to "Swim" across or "Pay" for a ferry.`
    Depending on what the user inputs, follow along with your story and save their choice.
* At the end of the steps you've set up, tell their story based off the actions they chose.
* For added difficulty, add health/damage to the game. You can also add branching choices: If they chose to pay for a ride, they might not be able to afford something later. Or if they chose to avoid a fight, it adds new options that weren't accessible if they did choose to fight.

Design recommendations:

* Think about classes to model the parts of the game.
* Think about how you may allow changes, such as adding more challenges.
