import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Messages {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    // menu printing
    public static void printMenu(boolean printWarn) throws URISyntaxException, IOException {
        if (printWarn) {
            printWarning();
        }
        System.out.println("1 - Open file");
        System.out.println("2 - Find file");
        System.out.println("3 - Create file");
        System.out.println("4 - Delete file");
        System.out.println("5 - See info about file");
        System.out.println("6 - Rename file");
        System.out.println("7 - Copy file");
        System.out.println("8 - Browse the folder");
        System.out.println("9 - Create new folder");
        System.out.println("10 - Counting occurrences of a word in file");
        System.out.println("q - Quite");
        System.out.print("Choose the action: ");
    }

    // warning printing
    public static void printWarning() {
        System.out.println(ANSI_RED + "Attention!\nThis version of the program is not a full-fledged commercial version" + ANSI_RESET);
    }

    // parting printing
    public static void goodbye(boolean printWarn) {
        System.out.println("\nGoodbye!");
        if (printWarn) {
            printWarning();
        }
    }

    // printing successful message
    public static void success() {
        System.out.println(ANSI_GREEN + "Action finished successfully!" + ANSI_RESET);
    }

    // printing unsuccessful message
    public static void failed() {
        System.out.println(ANSI_YELLOW + "Action failed!" + ANSI_RESET);
    }
}
