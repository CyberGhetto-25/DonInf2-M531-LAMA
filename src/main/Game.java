package main;

import utils.Array2Dprinter;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Location[][] map = new Location[4][3];

    private int playerRow = 1;
    private int playerCol = 1;
    private CommandManager commandManager;
    private Player player; // Add this line


    public Game() {
        System.out.println("Initializing game...");
        commandManager = new CommandManager();


        // map
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                map[r][c] = new Location("Zone " + r + "," + c, "You are in zone [" + r + "," + c + "]", false, false, false, null);
            }
        }

// Row 0
        map[0][0] = new Location("Place du midi", "A large esplanade where the worst mercenaries gather.", false, false, false, null);
        map[0][1] = new Location("Viseu", "The capital of Queen Léa's kingdom.", false, false, false, null);
        map[0][2] = new Location("Cactus Valley", "A cactus garden only the true warriors dare to explore.", false, false, false, null);

// Row 1
        map[1][0] = new Location("Mount Pichincha", "The highest volcano on our planet.", false, false, false, null);
        map[1][1] = new Location("Middle of the World", "A city that splits the Earth in two.", false, false, false, null);
        map[1][2] = new Location("Playa del sol", "You've reached the seashore, look around to access it.", true, false, false, null);

// Row 2
        map[2][0] = new Location("Pradoumaye", "A mountainous village district whose residents are reluctant to go to war.", false, false, false, null);
        map[2][1] = new Location("Zampelet", "A steep road that gives no rest to players who venture there.", false, false, false, null);
        map[2][2] = new Location("Redin Industrial Zone", "A vast area where ravers and players gather.", true, false, false, null);

// Row 3
        map[3][0] = new Location("Forgotten Swamps", "Humid lands inhabited by mysterious creatures and strange mist.", false, false, false, null);
        map[3][1] = new Location("Whispering Forest", "A dense forest where the trees seem to whisper ancient secrets.", false, false, false, null);
        map[3][2] = new Location("Crystal Labyrinth", "A maze with shimmering walls that defies all logic.", false, false, false, null);

// Adding key items
        map[1][0].addItem(new KeyItem("Beach Key", "Rusty key for the beach.", "Playa del sol"));
        map[2][0].addItem(new KeyItem("Redin Key", "Copper key for the industrial zone.", "Redin Industrial Zone"));

        getCurrentLocation().setPlayerHere(true);
        getCurrentLocation().setVisited(true);

        // NOUVEAU : Définition de l'énigme concrète pour la "Place du midi" (map[0][0])
        // Création de l'objet qui sera la récompense
        Item rewardForRiddle = new Item("Ancient Coin", "A rare coin with unknown symbols on it.");

        // Création de l'énigme
        Riddle firstRiddle = new Riddle(
                "I am a town that splits the Earth in two. What am I?", // La question
                "Middle of the World", // La réponse exacte attendue
                rewardForRiddle // L'objet que le joueur recevra s'il trouve la bonne réponse
        );

        map[0][0].setRiddle(firstRiddle);

        Item teleportCrystal = new Item("Teleport Crystal", "A powerful crystal blinking with arcane light.");
        map[3][2].addItem(teleportCrystal);

        // Register commands
        commandManager.registerCommand("move", new MoveCommand());
        commandManager.registerCommand("look", new LookCommand());
        commandManager.registerCommand("help", new HelpCommand());
        commandManager.registerCommand("map", new MapCommand());
        commandManager.registerCommand("take", new TakeCommand());
        commandManager.registerCommand("use", new UseCommand());
        commandManager.registerCommand("solve", new SolveCommand());
        commandManager.registerCommand("inventory", new InventoryCommand());

        this.player = new Player(getCurrentLocation());
    }

    public void run() {
         // boulce jeu principale
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Type 'help' to see available commands.");

        while (true) {
            System.out.print("> ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) break;

            boolean commandAlreadyRegistered = commandManager.getCommands().containsKey("teleport");
            boolean playerPossesesCrystal = player.hasItem("Teleport Crystal");

            if(!commandAlreadyRegistered && playerPossesesCrystal){
                commandManager.registerCommand("teleport",new TeleportCommand());
            }

            commandManager.execute(input, this, player); // <-- on le passe ici
        }

        System.out.println("Thanks for playing!");
    }
    public Player getPlayer() {
        return player; // Add this getter method
    }

    public void movePlayer(String direction) {
        int newRow = playerRow;
        int newCol = playerCol;
// mise à jour coordonnées
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

        if (newRow < 0 || newRow >= 4 || newCol < 0 || newCol >= 3) {
            System.out.println("Impossible to move there.");
            return;
        }

        Location target = map[newRow][newCol];
        if (target.isLocked()) {
            System.out.println("Zone locked.");
            return;
        }
// mise à jour position player
        map[playerRow][playerCol].setPlayerHere(false);
        playerRow = newRow;
        playerCol = newCol;
        target.setPlayerHere(true);
        target.setVisited(true);
        System.out.println(target.getName());
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

    public ArrayList<Location> getAdjacent(Location location) {
        int row = -1, col = -1;

        // Rechercher la position du Location dans la map
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                if (map[r][c].equals(location)) {
                    row = r;
                    col = c;
                    break;
                }
            }
        }

        ArrayList<Location> adjacent = new ArrayList<>();

        if (row == -1 || col == -1) {
            return null; // non trouvé
        }

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0 -> { // north
                    if (row > 0) adjacent.add(map[row - 1][col]);
                }
                case 1 -> { // south
                    if (row < map.length - 1) adjacent.add(map[row + 1][col]);
                }
                case 2 -> { // east
                    if (col < map[0].length - 1) adjacent.add(map[row][col + 1]);
                }
                case 3 -> { // west
                    if (col > 0) adjacent.add(map[row][col - 1]);
                }
            }
        }

        return adjacent;
    }

    public Location getLocationByName(String name){
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                if (map[r][c].getName().equalsIgnoreCase(name)) {
                    return map[r][c];
                }
            }
        }
        return null;
    }

    public int getLocationCol(Location location){
        for(int i = 0;i < map[0].length; i++){
            if(map[0][i].getName().equals(location.getName())) return i;
            if(map[1][i].getName().equals(location.getName())) return i;
            if(map[2][i].getName().equals(location.getName())) return i;
        }
        return -1;
    }
    public int getLocationRow(Location location){
        for(int i = 0;i < map.length; i++){
            if(map[i][0].getName().equals(location.getName())) return i;
            if(map[i][1].getName().equals(location.getName())) return i;
            if(map[i][2].getName().equals(location.getName())) return i;
        }
        return -1;
    }

    public void setPlayerCol(int playerCol) {
        this.playerCol = playerCol;
    }

    public void setPlayerRow(int playerRow) {
        this.playerRow = playerRow;
    }

}