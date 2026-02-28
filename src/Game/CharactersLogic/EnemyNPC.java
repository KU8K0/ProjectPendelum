package Game.CharactersLogic;

public class EnemyNPC extends NPC {

    public EnemyNPC(String id, String name) {
        super(id, name);
    }

    @Override
    public String talk(String locationId) {
        return formatDialog(name,
                DialogManager.getDialog(id, "default"));
    }
}