package main;

import java.util.List;

public class LookCommand implements ICommand {
    @Override
    public void execute(String[] args, Game game, Player player) {
        Location loc = game.getCurrentLocation();
        System.out.println("You are in: " + loc.getName());
        System.out.println(loc.getDescription());

        // vérifie items dans la location et les affichent
        List<Item> itemsInLocation = loc.getItems();
        if (itemsInLocation.isEmpty()) {
            System.out.println("There are no items here.");
        } else {
            System.out.println("You see the following items:");
            for (Item item : itemsInLocation) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }
    }
}