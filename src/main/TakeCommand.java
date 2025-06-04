package main;

import java.util.List;

public class TakeCommand implements ICommand {

    @Override
    public void execute(String[] args, Game game, Player player) { //vérifie si un nom d'objet a été fourni
        if (args.length < 2) {
            System.out.println("Take what?");
            return;
        }
        //construit le nom complet de l'objet
        StringBuilder itemNameBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            itemNameBuilder.append(args[i]);
            if (i < args.length - 1) {
                itemNameBuilder.append(" "); // Add space between words
            }
        }

    String itemName = itemNameBuilder.toString(); //nom de l'objet à prendre
    Location current = game.getCurrentLocation();
    List<Item> items = current.getItems(); //liste items dans la zone
        //parcourt les objets de la zone pour trouver celui à prendre
        for (Item item : items) {
        if (item.getName().equalsIgnoreCase(itemName)) { // si l'objet correspond
            game.getPlayer().addItem(item);
            current.removeItem(item.getName());
            System.out.println("You took the " + item.getName());
            return;
        }
    }

        System.out.print("No such item here.");
}
}
