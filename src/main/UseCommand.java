package main;

import java.util.Arrays;

public class UseCommand implements ICommand{
    @Override
    public void execute(String[] args, Game game, Player player) {
        if (args.length < 2) {
            System.out.println("Use what?");
            return;
        }

        String itemName = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        Item item = player.getItemByName(itemName);

        if (item == null) {
            System.out.println("You don't have that item.");
            return;
        }

        if (item instanceof KeyItem key) {
            Location current = game.getCurrentLocation();
            Location adjacentUnlockable = current.isAdjacentLocationUnlockable(game, key);
            if (adjacentUnlockable != null){ // vérifie que la location est vérrouillée
                adjacentUnlockable.unlock();
                player.removeItem(key);
                System.out.println("You unlocked the zone with the " + key.getName());
            } else {
                System.out.println("No zone to be unlocked near here.");
            }
        } else {
            System.out.println("This item can't be used in this way.");
        }
    }
}

