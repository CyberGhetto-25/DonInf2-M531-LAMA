package main;

public class InventoryCommand implements ICommand{
    @Override
    public void execute(String[] args, Game game, Player player) {

        if(player.getInventory().isEmpty()){
            System.out.println("You don't have anything m8.");
        }else{
            System.out.println("You currently have :");
            for(Item item: player.getInventory()){
                System.out.println("- "+item.getName()+" : "+item.getDescription());
            }
        }
    }
}
