package main;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Item> inventory;
    private Location currentLocation;
    //initialise le joueur avec position de départ et inventaire vide
    public Player(Location startLocation) {
        this.currentLocation = startLocation;
        this.inventory = new ArrayList<>();
    }

    public void addItem(Item item) {
        inventory.add(item);
    }
    //vérifie si le joueur possède un objet par son nom
    public boolean hasItem(String name) {
        return inventory.stream().anyMatch(i -> i.getName().equalsIgnoreCase(name));
    }

    public Item getItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) return item;
        }
        return null;
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }
//Renvoie objet de l'inventaire par son nom
    public Item getItemByName(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}
