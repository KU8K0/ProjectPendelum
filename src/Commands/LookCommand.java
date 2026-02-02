package Commands;

import Game.CharactersLogic.Player;
import Game.WorldLogic.Location;

public class LookCommand implements Command {

    private Player player;

    public LookCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {

        Location current = player.getCurrentLocation();

        System.out.println("=== ROZHLED ===");
        System.out.println(current.getName());
        System.out.println(current.getDescription());
        System.out.println();
        System.out.println("Východy:");

        for (String id : current.getNeighborIds()) {
            System.out.println("- " + id);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
