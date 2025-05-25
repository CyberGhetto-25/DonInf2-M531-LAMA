package main;

public class MapCommand implements ICommand{
    @Override
    public void execute(String[] args, Game game) {
        game.printMap();
    }
}
