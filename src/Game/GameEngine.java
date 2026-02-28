package Game;

import Game.WorldLogic.*;
import Game.CharactersLogic.*;
import Commands.*;

import java.util.Scanner;

public class GameEngine {

    private World world;
    private Player player;
    private CommandParser parser;
    private boolean isRunning = true;

    public GameEngine(World world) {
        this.world = world;
        this.player = new Player();
        this.player.setCurrentLocation(world.getLocation(world.getStartLocationId()));
        this.parser = new CommandParser(player, world);
    }

    public void start() {

        Scanner scanner = new Scanner(System.in, "UTF-8");

        System.out.println("Vítej v Silrae, Silvere Nexus.");
        System.out.println(player.getCurrentLocation().getFullDescription(false));

        while (isRunning) {

            System.out.print("\n>> ");
            String input = scanner.nextLine();

            Command command = parser.parseCommand(input);

            if (command != null) {

                String result = command.execute();
                System.out.println(result);

                if (command.isExit()) {
                    isRunning = false;
                }

            } else {
                System.out.println("Neznámý příkaz. Zkus 'pomoc'.");
            }

            checkDanger();
        }
    }

    private void checkDanger() {

        Location loc = player.getCurrentLocation();

        if (loc.isDangerous() && !player.getInventory().hasItem("item_cloak")) {

            System.out.println(
                    "\n[VAROVÁNÍ] Kamery sledují oblast. " +
                            "Bez ochranného pláště můžeš být odhalen.\n" +
                            "Tip: použij příkaz 'stealth'."
            );
        }
    }
}