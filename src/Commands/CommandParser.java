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

        if (commandWord.equals("jdi")) {
            if (parts.length > 1) {
                return new MoveCommand(player, world, parts[1]);
            } else {
                System.out.println("Kam chceš jít? Musíš napsat název lokace.");
                return null;
            }
        }

        return null;
    }
}
