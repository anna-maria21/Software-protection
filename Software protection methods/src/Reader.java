import java.io.*;
import java.util.ArrayList;

public class Reader {

    private ArrayList<String> listOfKeys = new ArrayList<>();
    private static final String key = "kpi";

    // reading available keys
    public void readFile() throws IOException {

        File file = new File("src/available_keys.txt");
        Runtime.getRuntime().exec("attrib +H " + file);

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            listOfKeys.add(line);
        }
        fr.close();

    }

    // validating the key
    public boolean isCorrectKey(StringBuilder enteredKey) {

        Coder coder = new Coder(new StringBuilder(key));
        boolean notExists = false;
        StringBuilder encryptedKey = coder.encrypt(enteredKey);
        if (!listOfKeys.contains(encryptedKey.toString())) {
            notExists = true;
        }

        return notExists;
    }

    // calling selected functions
    public void chooseAction(String action) throws IOException {

        switch (action) {
            case "1" -> WorkWithFile.openFile();
            case "2" -> WorkWithFile.findFile();
            case "3" -> WorkWithFile.createFile();
            case "4" -> WorkWithFile.deleteFile();
            case "5" -> WorkWithFile.showInfoFile();
            case "6" -> WorkWithFile.renameFile();
            case "7" -> WorkWithFile.copyFile();
            case "8" -> {
                File baseDirectory = new File(WorkWithFile.readPath());
                WorkWithFile.browseFolder(baseDirectory);
            }
            case "9" -> WorkWithFile.createFolder();
            case "10" -> WorkWithFile.searchInFile();
            case "q" -> Messages.goodbye(false);
            default -> System.out.println("Wrong input!");
        }

    }

}
