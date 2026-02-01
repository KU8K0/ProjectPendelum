package Commands;

public class EndCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Spojení se Silrae bylo ukončeno...");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
