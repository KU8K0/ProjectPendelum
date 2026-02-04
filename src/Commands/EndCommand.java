package Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EndCommand implements Command {

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("Resources/outro.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();

        } catch (IOException e) {
            return "CONNECTION LOST...";
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}