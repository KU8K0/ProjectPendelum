package Commands;

import Game.CharactersLogic.Player;
import Game.WorldLogic.Location;
import Game.WorldLogic.World;

public class LookCommand implements Command {

    private World world;
    private Player player;

    public LookCommand(Player player, World world) {
        this.player = player;
        this.world = world;
    }

    @Override
    public String execute() {
        Location current = player.getCurrentLocation();
        StringBuilder sb = new StringBuilder();

        sb.append("=== ROZHLED ===\n");
        sb.append(current.getName().toUpperCase()).append("\n");
        sb.append(current.getDescription()).append("\n");

        sb.append(current.getItemsDescription());
        sb.append(current.getNPCDescription());

        sb.append("\nVÝCHODY:\n");
        for (String id : current.getNeighborIds()) {
            Location neighbor = player.getCurrentLocation() != null
                    ? world.getLocation(id)
                    : null;

            if (neighbor != null) {
                sb.append(" - ").append(neighbor.getName()).append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}