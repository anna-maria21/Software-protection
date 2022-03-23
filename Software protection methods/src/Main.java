import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        // key reading
        System.out.print("Enter the key to unlock some functions: ");
        Scanner scanner = new Scanner(System.in);
        StringBuilder enteredKey = new StringBuilder(scanner.nextLine().toLowerCase());

        // key verification
        Reader reader = new Reader();
        reader.readFile();
        boolean notExists = reader.isCorrectKey(enteredKey);

        if (notExists) {
            // launch browser on developer page
            Desktop.getDesktop().browse(new URI("https://github.com/anna-maria21"));
        }

        // menu display
        Messages.printMenu(notExists);
        String action = scanner.nextLine();

        while (!Objects.equals(action, "q")) {
            reader.chooseAction(action);
            Messages.printMenu(notExists);
            action = scanner.nextLine();
        }

        Messages.goodbye(notExists);

    }


}
