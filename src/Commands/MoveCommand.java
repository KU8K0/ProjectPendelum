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
    public void execute() {

        Location current = player.getCurrentLocation();
        Location matchedLocation = null;

        for (String neighborId : current.getNeighborIds()) {

            Location neighbor = world.getLocation(neighborId);
            String normalizedLocationName = normalize(neighbor.getName());

            if (normalizedLocationName.equals(targetInput)) {
                matchedLocation = neighbor;
                break;
            }
        }

        if (matchedLocation != null) {
            player.setCurrentLocation(matchedLocation);
            System.out.println("Přesunul ses do: " + matchedLocation.getName());
            System.out.println(matchedLocation.getDescription());
        } else {
            System.out.println("Tato lokace není odsud přístupná.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String normalize(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalized.toLowerCase().trim();
    }
}
