package main;

import java.util.List;

public class LookCommand implements ICommand {
    @Override
    public void execute(String[] args, Game game, Player player) {
        Location loc = game.getCurrentLocation();
        System.out.println("You are in: " + loc.getName());
        System.out.println(loc.getDescription());

        // Afficher les objets dans la zone
        List<Item> itemsInLocation = loc.getItems();
        if (itemsInLocation.isEmpty()) {
            System.out.println("There are no items here.");
        } else {
            System.out.println("You see the following items:");
            for (Item item : itemsInLocation) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }

        // NOUVEAU : Afficher l'énigme si présente et non résolue
        Riddle riddle = loc.getRiddle();
        if (riddle != null && !riddle.isSolved()) {
            System.out.println("\nThere's a riddle here!");
            System.out.println("Riddle: " + riddle.getQuestion());
            System.out.println("You can try to solve it using the 'solve' command.");
        } else if (riddle != null && riddle.isSolved()) {
            System.out.println("\nThe riddle here has already been solved.");
        }
    }
}