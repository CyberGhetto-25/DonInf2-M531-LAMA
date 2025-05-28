package main;

public interface ICommand {
    void execute(String[] args, Game game, Player player);
}
