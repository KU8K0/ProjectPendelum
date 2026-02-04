package Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HelpCommand implements Command {

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("Resources/help.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();

        } catch (IOException e) {
            return "HELP_ERROR: Nelze načíst nápovědu.";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}