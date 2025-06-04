package main;

public class KeyItem extends Item {
    private String unlocksZoneName;

    public KeyItem(String name, String description, String unlocksZoneName) {
        super(name, description);
        this.unlocksZoneName = unlocksZoneName;
    }

    public String getTargetLocationName() {
        return unlocksZoneName;
    }

    public boolean unlocks(Location loc) {
        return loc.getName().equalsIgnoreCase(unlocksZoneName);
    }
}
