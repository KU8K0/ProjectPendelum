package Commands;

import Game.CharactersLogic.Player;
import Game.WorldLogic.Location;

public class LookCommand implements Command {

    private Player player;

    public LookCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        Location current = player.getCurrentLocation();
        StringBuilder sb = new StringBuilder();

        sb.append("=== ROZHLED ===\n");
        sb.append(current.getName().toUpperCase()).append("\n");
        sb.append(current.getDescription()).append("\n");

        sb.append(current.getItemsDescription());

        sb.append("\nVýchody:\n");
        for (String id : current.getNeighborIds()) {
            sb.append("- ").append(id).append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}