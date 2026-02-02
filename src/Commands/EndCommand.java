package Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EndCommand implements Command {

    @Override
    public void execute() {

        try (BufferedReader reader = new BufferedReader(new FileReader("Resources/outro.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                Thread.sleep(100); // malý efekt postupného vypisu
            }

        } catch (IOException e) {
            System.out.println("CONNECTION LOST...");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
