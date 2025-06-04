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
            //faire un boucle qui check chaque zone autour et si c'est la bonne la déverrouiller
            if (key.getTargetLocationName().equalsIgnoreCase(current.getName())) { // vérifie si la clé est pour CETTE location
                if (current.isLocked()) { // vérifie que la location est vérrouillée
                    current.unlock();
                    player.removeItem(key);
                    System.out.println("You unlocked the zone with the " + key.getName());
                } else {
                    System.out.println("This zone is not locked.");
                }
            } else {
                System.out.println("This key doesn't fit here. It's for " + key.getTargetLocationName() + ".");
            }
        } else {
            System.out.println("This item can't be used in this way.");
        }
    }
}
