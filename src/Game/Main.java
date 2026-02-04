package Game;

import Commands.Command;
import Commands.CommandParser;
import Game.CharactersLogic.Player;
import Game.WorldLogic.Location;
import Game.WorldLogic.World;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        World world = new World();

        world.loadFromJson("Resources/gamedata.json");

        if (world.getStartLocationId() == null) {
            System.out.println("Chyba: Nepodařilo se načíst svět (zkontroluj cestu k souboru 'gamedata.json').");
            return;
        }

        Player player = new Player();
        Location startLocation = world.getLocation(world.getStartLocationId());

        if (startLocation == null) {
            System.out.println("Chyba: Startovní lokace nenalezena.");
            return;
        }

        player.setCurrentLocation(startLocation);

        CommandParser parser = new CommandParser(player, world);
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== PROJECT PENDELUM ===");
        System.out.println("Jsi v: " + player.getCurrentLocation().getName());
        System.out.println(player.getCurrentLocation().getDescription());
        System.out.println("------------------------------------------------");

        boolean running = true;
        while (running) {
            System.out.print("\n> ");
            String input = scanner.nextLine();

            Command command = parser.parseCommand(input);

            if (command != null) {
                String result = command.execute();
                if (result != null && !result.isEmpty()) {
                    System.out.println(result);
                }

                if (command.isExit()) {
                    running = false;
                }
            } else {
                System.out.println("Neznámý příkaz. Napiš 'pomoc' pro seznam příkazů.");
            }
        }

        scanner.close();
    }
}