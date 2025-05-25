package main;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final Map<String, ICommand> commands = new HashMap<>();

    public void registerCommand(String name, ICommand command) {
        commands.put(name.toLowerCase(), command);
    }

    public void execute(String input, Game game) {
        String[] parts = input.trim().split(" ");
        if (parts.length == 0) return;

        String commandName = parts[0].toLowerCase();
        ICommand command = commands.get(commandName);
        if (command != null) {
            command.execute(parts, game);
        } else {
            System.out.println("Unknown command. Type 'help' for available commands.");
        }
    }

    public void listCommands() {
        System.out.println("Available commands:");
        for (String cmd : commands.keySet()) {
            System.out.println("- " + cmd);
        }
    }
}
