package main;

public class TeleportCommand implements ICommand {
    @Override
    public void execute(String[] args, Game game, Player player) {
        if (args.length < 2) {
            System.out.println("Teleport where?");
            return;
        }
        //construit le nom complet de l'objet
        StringBuilder locationNameBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            locationNameBuilder.append(args[i]);
            if (i < args.length - 1) {
                locationNameBuilder.append(" "); // Add space between words
            }
        }

        String locationName = locationNameBuilder.toString();
        Location teleportLocation = game.getLocationByName(locationName);

        if(teleportLocation.isGrayedOut()){
            System.out.println("The crystal turns red as if angered.");
        }else{
            System.out.println("The crystal starts to glow intensely.");

            game.setPlayerCol(game.getLocationCol(teleportLocation));
            game.setPlayerRow(game.getLocationRow(teleportLocation));

            game.getCommandManager().execute("look",game, player);
        }
    }
}
