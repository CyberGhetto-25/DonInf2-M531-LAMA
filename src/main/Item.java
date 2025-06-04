package main;

public class Item {
    private String name;
    private String description;
    private boolean usable;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.usable = usable;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUsable() {
        return usable;
    }

    public void use() {
        if (usable) {
            System.out.println("You use the " + name + ". " + description);
        } else {
            System.out.println("You can't use the " + name + ".");
        }
    }
}
