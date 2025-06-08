package main;

import java.util.Arrays;

public class SolveCommand implements ICommand {

    @Override
    public void execute(String[] args, Game game, Player player) {
        Location current = game.getCurrentLocation();
        Riddle riddle = current.getRiddle();

        if (riddle == null) {
            System.out.println("There's no riddle here to solve.");
            return;
        }

        if (riddle.isSolved()) {
            System.out.println("This riddle has already been solved!");
            return;
        }

        // Si le joueur ne donne pas de réponse
        if (args.length < 2) {
            System.out.println("What's your answer? (e.g., 'solve your answer here')");
            System.out.println("Riddle: " + riddle.getQuestion()); // Rappelle la question
            return;
        }

        // Reconstruit la réponse du joueur à partir des arguments
        String playerAnswer = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        if (riddle.checkSolution(playerAnswer)) {
            System.out.println("Correct! You solved the riddle!");
            player.addItem(riddle.getRewardItem()); // Donne l'objet récompense au joueur
            System.out.println("You received: " + riddle.getRewardItem().getName() + " (" + riddle.getRewardItem().getDescription() + ")");
            riddle.setSolved(true); // Marque l'énigme comme résolue
        } else {
            System.out.println("That's not the right answer. Try again!");
        }
    }
}