package Commands;

import Game.CharactersLogic.Player;
import Game.ItemLogic.Item;

public class DropCommand implements Command {

    private Player player;
    private String itemName;

    public DropCommand(Player player, String itemName) {
        this.player = player;
        this.itemName = itemName;
    }

    @Override
    public String execute() {
        Item item = player.getInventory().getItem(itemName);

        if (item == null) {
            return "Takovou věc u sebe nemáš.";
        }

        player.getInventory().removeItem(item);

        player.getCurrentLocation().addItem(item);

        return "Odhodil jsi: " + item.getName();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}