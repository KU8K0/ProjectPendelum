package Game.CharactersLogic;

import Game.CharactersLogic.DialogManager;

public class FriendlyNPC extends NPC {

    public FriendlyNPC(String id, String name) {
        super(id, name);
    }

    @Override
    public String talk(String locationId) {
        return DialogManager.getDialog(id, locationId);
    }
}
