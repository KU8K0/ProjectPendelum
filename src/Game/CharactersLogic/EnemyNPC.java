package Game.CharactersLogic;

import Game.CharactersLogic.DialogManager;

public class EnemyNPC extends NPC {

    public EnemyNPC(String id, String name) {
        super(id, name);
    }

    @Override
    public String talk(String locationId) {
        return DialogManager.getDialog(id, "default");
    }

    public String hostile() {
        return DialogManager.getDialog(id, "hostile");
    }
}
