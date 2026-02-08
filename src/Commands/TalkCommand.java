package Commands;

import Game.CharactersLogic.Player;
import Game.CharactersLogic.NPC;
import Game.WorldLogic.Location;

public class TalkCommand implements Command {

    private Player player;
    private String target;

    public TalkCommand(Player player, String target) {
        this.player = player;
        this.target = target;
    }

    @Override
    public String execute() {
        Location location = player.getCurrentLocation();
        NPC npc = location.getNPC(target);

        if (npc == null) {
            return "Nikdo takový tu není.";
        }

        return npc.talk(location.getId());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}