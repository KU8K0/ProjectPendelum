package Commands;

import Game.Player;
import Game.World;
import Game.Location;

public class MoveCommand implements Command {

    private Player player;
    private World world;
    private String targetLocationName;

    public MoveCommand(Player player, World world, String targetLocationName) {
        this.player = player;
        this.world = world;
        this.targetLocationName = targetLocationName;
    }

    @Override
    public void execute() {

        Location current = player.getCurrentLocation();

        Location target = null;

        for (String neighborId : current.getNeighborIds()) {
            Location neighbor = world.getLocation(neighborId);
            if (neighbor.getName().equalsIgnoreCase(targetLocationName)) {
                target = neighbor;
                break;
            }
        }

        if (target != null) {
            player.setCurrentLocation(target);
            System.out.println("Přesunul ses do: " + target.getName());
            System.out.println(target.getDescription());
        } else {
            System.out.println("Tam se odsud nedostaneš.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
