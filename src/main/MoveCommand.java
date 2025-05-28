package main;

public class MoveCommand implements ICommand {
    @Override
    public void execute(String[] args, Game game, Player player) {
        if (args.length < 2) {
            System.out.println("Usage: move <direction>");
            return;
        }
        String direction = args[1].toLowerCase();
        game.movePlayer(direction);
    }
}
