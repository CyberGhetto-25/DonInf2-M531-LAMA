package main;

import utils.Array2Dprinter;

import java.util.Scanner;

public class Game {

    private Location[][] map = new Location[3][3];
    private int playerRow = 1;
    private int playerCol = 1;
    private CommandManager commandManager;

    public Game() {
        System.out.println("Initializing game...");
        commandManager = new CommandManager();

        // Init map
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                map[r][c] = new Location("Zone " + r + "," + c, "You are in zone [" + r + "," + c + "]", false, false, false);
            }
        }

        map[0][0] = new Location("Place du midi", "Une large esplanade sur laquelle se retrouvent les pires mercenaires", false, false, false);
        map[0][1] = new Location("Viseu", "La capitale du royaume de la reine Léa", false, false, false);
        map[0][2] = new Location("Cactus Valley", "Un jardin de cactus dans lequel seuls les vrais guerriers oseront s'y aventurer.", true, false, false);

        map[1][0] = new Location("Le volcan Pichinscha", "Le volcan le plus haut de notre planète.", false, false, false);
        map[1][1] = new Location("La moitié du monde", "Une ville qui coupe la terre en deux.", false, false, false);
        map[1][2] = new Location("La playa del Sol", "Vous voilé arrivé en bord de mer, regardez autour de vous pour y accéder.", true, false, false);

        map[2][0] = new Location("Pradoumaye", "Quartier d'un village montagneux dont les habitans sont réticents à la guerre", false, false, false);
        map[2][1] = new Location("Zampelet", "Une route abrupte qui ne laisse pas de répit aux joueurs qui s'y rendent.", false, false, false);
        map[2][2] = new Location("Zone industrielle de Redin", "Vaste zone dans lesquels ravers et joueurs se rencontrent.", true, false, false);

        map[0][1].lock(); // Exemple : une zone verrouillée
        getCurrentLocation().setPlayerHere(true);
        getCurrentLocation().setVisited(true);

        // Register commands
        commandManager.registerCommand("move", new MoveCommand());
        commandManager.registerCommand("look", new LookCommand());
        commandManager.registerCommand("help", new HelpCommand());
        commandManager.registerCommand("map", new MapCommand());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Type 'help' to see available commands.");
        while (true) {
            System.out.print("> ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) break;

            commandManager.execute(input, this);
        }
        System.out.println("Thanks for playing!");
    }

    public void movePlayer(String direction) {
        int newRow = playerRow;
        int newCol = playerCol;

        switch (direction) {
            case "north" -> newRow--;
            case "south" -> newRow++;
            case "east" -> newCol++;
            case "west" -> newCol--;
            default -> {
                System.out.println("Unknown direction.");
                return;
            }
        }

        if (newRow < 0 || newRow >= 3 || newCol < 0 || newCol >= 3) {
            System.out.println("Impossible to move there.");
            return;
        }

        Location target = map[newRow][newCol];
        if (target.isLocked()) {
            System.out.println("Zone locked.");
            return;
        }

        map[playerRow][playerCol].setPlayerHere(false);
        playerRow = newRow;
        playerCol = newCol;
        target.setPlayerHere(true);
        target.setVisited(true);
        System.out.println(target.getDescription());
    }

    public void printMap() {
        String result = Array2Dprinter.print2DArray(map, playerRow, playerCol);
        System.out.println(result);
    }

    public Location getCurrentLocation() {
        return map[playerRow][playerCol];
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

}