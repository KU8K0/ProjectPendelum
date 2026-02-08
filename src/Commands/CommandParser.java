package Commands;

import Game.CharactersLogic.Player;
import Game.WorldLogic.World;

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
        String argument = (parts.length > 1) ? parts[1].trim() : null;

        switch (commandWord) {
            case "jdi":
                if (argument != null) {
                    return new MoveCommand(player, world, argument);
                } else {
                    System.out.println("Kam chceš jít?");
                    return null;
                }

            case "rozhledni":
                return new LookCommand(player);

            case "mapa":
                return new MapCommand(player);

            case "inventar":
                return new InventoryCommand(player);

            case "vezmi":
                if (argument != null) {
                    return new TakeCommand(player, argument);
                } else {
                    System.out.println("Co chceš vzít?");
                    return null;
                }

            case "poloz":
                if (argument != null) {
                    return new DropCommand(player, argument);
                } else {
                    System.out.println("Co chceš položit?");
                    return null;
                }

            case "prozkoumej":
                if (argument != null) {
                    return new InspectCommand(player, argument);
                } else {
                    System.out.println("Co chceš prozkoumat?");
                    return null;
                }

            case "mluv":
                if (argument != null) {
                    return new TalkCommand(player, argument);
                } else {
                    System.out.println("S kým chceš mluvit?");
                    return null;
                }

            case "pouzij":
                if (argument != null) {
                    return new UseCommand(player, world, argument);
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