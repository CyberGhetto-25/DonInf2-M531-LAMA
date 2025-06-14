package main;
import utils.IPrintable;
import utils.Array2Dprinter;

import java.util.ArrayList;
import java.util.List;

public class Location implements IPrintable {

    private String name;
    private String description;
    private boolean isLocked;
    private boolean visited;
    private boolean playerIsHere;
    private List<Item> items = new ArrayList<>();
    private Riddle riddle;

    public Location (String name, String description, boolean isLocked, boolean visited, boolean playerIsHere, Riddle riddle) {
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;
        this.visited = visited;
        this.playerIsHere = playerIsHere;
        this.riddle = null;

    }

    // NOUVEAU : Setter pour l'énigme
    public void setRiddle(Riddle riddle) {
        this.riddle = riddle;
    }

    // NOUVEAU : Getter pour l'énigme
    public Riddle getRiddle() {
        return riddle;
    }

        // Pour déplacer le joueur
    public void setPlayerHere(boolean playerHere) {
        this.playerIsHere = playerHere;
    }
 
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void unlock() {
        this.isLocked = false;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String name) {
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(name)) {
                items.remove(i);
                return i;
            }
        }
        return null;
    }

    @Override
    public String getPrintableString() {

        if (playerIsHere) return getName();
        return visited ? getName() : "?";
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

    public Location isAdjacentLocationUnlockable(Game game, KeyItem key){

        ArrayList<Location> adjacents = game.getAdjacent(this);

        for(Location location: adjacents){
            if(key.getTargetLocationName().equalsIgnoreCase(location.getName())
                    && location.isLocked()){
                return location;
            }
        }

        return null;
    }

}
