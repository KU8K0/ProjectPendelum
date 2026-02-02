package Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HelpCommand implements Command {

    @Override
    public void execute() {

        try (BufferedReader reader = new BufferedReader(new FileReader("Resources/help.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("HELP_ERROR: Nelze načíst nápovědu.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
