package Commands;

public class StealthCommand implements Command {
    @Override
    public String execute() {
        return "";
        //TODO Stealth logic (minigame)
    }

    @Override
    public boolean isExit() {
        return false;
    }
}