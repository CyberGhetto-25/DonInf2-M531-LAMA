package main;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final Map<String, ICommand> commands = new HashMap<>();

    public void registerCommand(String name, ICommand command) {
        commands.put(name, command);
    }

    public void execute(String input, Game game, Player player) {
        String[] parts = input.trim().split(" ");
        if (parts.length == 0) return; // gestion entrée utilisateur
        String commandName = parts[0]; //nom commande
        ICommand command = commands.get(commandName);
        if (command != null) {
            command.execute(parts, game, player);
        } else {
            System.out.println("Unknown command.");
        }
    }

    public void listCommands() { // affiche les commandes possibles
        System.out.println("Available commands:");
        for (String cmd : commands.keySet()) {
            System.out.println("- " + cmd);
        }
    }
}
