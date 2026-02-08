package Commands;

import Game.CharactersLogic.Player;
import Game.WorldLogic.World;
import Game.WorldLogic.Location;
import java.text.Normalizer;

public class MoveCommand implements Command {

    private Player player;
    private World world;
    private String targetInput;

    public MoveCommand(Player player, World world, String targetInput) {
        this.player = player;
        this.world = world;
        this.targetInput = normalize(targetInput);
    }

    @Override
    public String execute() {
        Location current = player.getCurrentLocation();
        Location matchedLocation = null;

        for (String neighborId : current.getNeighborIds()) {
            Location neighbor = world.getLocation(neighborId);

            if (neighbor != null) {
                String normName = normalize(neighbor.getName());
                String normId = normalize(neighborId);

                if (normName.contains(targetInput) || normId.equals(targetInput)) {
                    matchedLocation = neighbor;
                    break;
                }
            }
        }

        if (matchedLocation != null) {
            player.setCurrentLocation(matchedLocation);


            return "------------------------------------------------\n" +
                    "Jsi v: " + matchedLocation.getName() + "\n" +
                    matchedLocation.getDescription() + "\n" +
                    matchedLocation.getItemsDescription() +
                    "\n------------------------------------------------";
        } else {
            return "Tam odsud nemůžeš jít.";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String normalize(String input) {
        if (input == null) return "";
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalized.toLowerCase().trim();
    }
}