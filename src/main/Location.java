package main;
import utils.IPrintable;
import utils.Array2Dprinter;

public class Location implements IPrintable {

    private String name;
    private String description;
    private boolean isLocked;
    private boolean visited;
    private boolean playerIsHere;

    public Location (String name, String description, boolean isLocked, boolean visited, boolean playerIsHere) {
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;
        this.visited = visited;
        this.playerIsHere = playerIsHere;

    }

        // Pour déplacer le joueur
    public void setPlayerHere(boolean playerHere) {
        this.playerIsHere = playerHere;
    }
 
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String getPrintableString() {

        if (playerIsHere) return "P";
        return visited ? "." : "#";
    }

    @Override
    public boolean isGrayedOut() {

        return !visited;

    } 
    public boolean isLocked() {
        return isLocked;
    }

    public String getDescription() {
        return description;
    }

    public String getName(){return name;}

    public void lock() {
        this.isLocked = true;
    }

}
