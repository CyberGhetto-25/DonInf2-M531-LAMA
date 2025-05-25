package main;

public class LookCommand implements ICommand {
    @Override
    public void execute(String[] args, Game game) {
        Location loc = game.getCurrentLocation();
        System.out.println("You are in: " + loc.getDescription());
    }
}
