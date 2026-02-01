package Commands;

import Game.Player;
import Game.World;

public class CommandParser {

    private Player player;
    private World world;

    public CommandParser(Player player, World world) {
        this.player = player;
        this.world = world;
    }

    public Command parseCommand(String input) {

        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {

            case "jdi":
                if (parts.length > 1) {
                    return new MoveCommand(player, world, parts[1]);
                } else {
                    System.out.println("Kam chceš jít?");
                    return null;
                }

            case "rozhledni":
                return new LookCommand(player);

            case "mapa":
                return new MapCommand(player);

            case "batoh":
            case "inventar":
                return new InventoryCommand(player);

            case "vezmi":
                if (parts.length > 1) {
                    return new TakeCommand(player, parts[1]);
                } else {
                    System.out.println("Co chceš vzít?");
                    return null;
                }

            case "mluv":
                if (parts.length > 1) {
                    return new TalkCommand(player, parts[1]);
                } else {
                    System.out.println("S kým chceš mluvit?");
                    return null;
                }

            case "pouzij":
                if (parts.length > 1) {
                    return new UseCommand(player, parts[1]);
                } else {
                    System.out.println("Co chceš použít?");
                    return null;
                }

            case "pomoc":
                return new HelpCommand();

            case "konec":
                return new EndCommand();

            default:
                return null;
        }
    }
}
