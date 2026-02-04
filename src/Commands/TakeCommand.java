package Commands;

import Game.CharactersLogic.Player;
import Game.ItemLogic.Item;

public class TakeCommand implements Command {

    private Player player;
    private String itemName;

    public TakeCommand(Player player, String itemName) {
        this.player = player;
        this.itemName = itemName;
    }

    @Override
    public String execute() {
        // Zatím testovací předmět
        Item item = new Item("temp", itemName, "Testovací předmět");
        return player.getInventory().addItem(item);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}