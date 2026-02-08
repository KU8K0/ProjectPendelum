package Commands;

import Game.CharactersLogic.Player;
import Game.ItemLogic.Item;
import Game.WorldLogic.Location;

public class TakeCommand implements Command {

    private Player player;
    private String itemName;

    public TakeCommand(Player player, String itemName) {
        this.player = player;
        this.itemName = itemName;
    }

    @Override
    public String execute() {
        Location location = player.getCurrentLocation();

        // 1. Najdi předmět v lokaci
        Item item = location.getItem(itemName);

        if (item == null) {
            return "Předmět '" + itemName + "' tu nevidíš.";
        }

        if (player.getInventory().isFull()) {
            return "Tvůj inventář je plný (max 3 předměty). Musíš něco zahodit.";
        }

        player.getInventory().addItem(item);
        location.removeItem(item);

        return "Vzal jsi: " + item.getName();
    }

    @Override
    public boolean isExit() { return false; }
}