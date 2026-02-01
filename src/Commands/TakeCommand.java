package Commands;

import Game.Player;
import Game.Item;

public class TakeCommand implements Command {

    private Player player;
    private String itemName;

    public TakeCommand(Player player, String itemName) {
        this.player = player;
        this.itemName = itemName;
    }

    @Override
    public void execute() {

        // Zatím testovací předmět
        Item item = new Item("temp", itemName, "Testovací předmět");

        player.getInventory().addItem(item);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
