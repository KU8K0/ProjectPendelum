package Commands;

public class HackCommand implements Command {
    @Override
    public String execute() {
        return "";
        //TODO Hack logic (minigame)
    }

    @Override
    public boolean isExit() {
        return false;
    }
}