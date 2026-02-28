package Game.CharactersLogic;

public class FriendlyNPC extends NPC {

    public FriendlyNPC(String id, String name) {
        super(id, name);
    }

    @Override
    public String talk(String locationId) {

        String context = "default";

        if (id.equals("npc_agnes")) {
            context = locationId.equals("loc_residential") ? "no_cloak" : "default";
        }

        String text = DialogManager.getDialog(id, context);
        return formatDialog(name, text);
    }
}