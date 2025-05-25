package main;

public class HelpCommand implements ICommand{
    @Override
    public void execute(String[] args, Game game) {
        game.getCommandManager().listCommands();
    }
}
