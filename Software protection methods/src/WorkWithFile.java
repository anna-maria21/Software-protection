import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

public class WorkWithFile {

    private static Scanner scanner = new Scanner(System.in);

    // reading file path
    public static String readPath() {
        System.out.print("Enter the full path to file (directory): ");
        String path = scanner.nextLine();
        return path;
    }

    // output file content
    public static void openFile() throws IOException {
        File file = new File(readPath());
        if (file.exists() && !file.isHidden()) {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            fr.close();
        } else {
            System.out.println("No file found!");
            Messages.failed();
        }
    }

    // creating new file
    public static void createFile() throws IOException {
        File file = new File(readPath());
        if (file.createNewFile()) {
            Messages.success();
        } else {
            Messages.failed();
        }
    }

    // deleting selected file
    public static void deleteFile() throws IOException {
        File file = new File(readPath());
        if (file.exists()) {
            file.delete();
            if (!file.exists()) {
                Messages.success();
            } else {
                Messages.failed();
            }
        } else {
            System.out.println("No file found!");
            Messages.failed();
        }
    }

    // printing info about selected file
    public static void showInfoFile() {
        File file = new File(readPath());
        if (file.exists()) {
            System.out.println("Name: " + file.getName());
            System.out.println("Parent folder: " + file.getParent());
            System.out.println("Hidden: " + file.isHidden());
            System.out.println("File: " + file.isFile());
            System.out.println("Directory: " + file.isDirectory());
            System.out.println("Length: " + file.length() + " bytes");

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            System.out.println("Last modified: " + sdf.format(file.lastModified()));
        } else {
            System.out.println("No file found!");
            Messages.failed();
        }
    }

    // file rename
    public static void renameFile() {
        System.out.print("Enter the full path to old named file: ");
        String oldName = scanner.nextLine();
        File file1 = new File(oldName);
        if (file1.exists()) {
            System.out.print("Enter the new name: ");
            String newName = oldName.substring(0, oldName.lastIndexOf("\\") + 1) + scanner.nextLine() + oldName.substring(oldName.lastIndexOf("."));
            File file2 = new File(newName);
            if (!file2.exists()) {
                file1.renameTo(file2);
                Messages.success();
            } else {
                System.out.println("This name for file is not available!");
                Messages.failed();
            }
        } else {
            System.out.println("No file found!");
            Messages.failed();
        }

    }

    // coping file
    public static void copyFile() throws IOException {
        System.out.print("Enter the full path of the file you want to copy: ");
        String oldFile = scanner.nextLine();
        File file1 = new File(oldFile);
        if (file1.exists()) {
            System.out.print("Enter the full path of the new file: ");
            String newFile = scanner.nextLine();
            File file2 = new File(newFile);
            if (!file2.exists()) {
                Files.copy(file1.toPath(), file2.toPath());
                Messages.success();
            } else {
                System.out.println("This name for file is not available!");
                Messages.failed();
            }
        } else {
            System.out.println("No file found!");
            Messages.failed();
        }
    }

    // output directory content
    public static void browseFolder(File baseDirectory) {
        if (baseDirectory.isDirectory()) {
            for (File file : baseDirectory.listFiles()) {
                if (file.isFile()) {
                    System.out.println("\t" + file.getName() + " - file");
                } else {
                    System.out.println(file.getName() + " - folder");
                    browseFolder(file);
                }
            }
        } else {
            System.out.println("It is not a directory!");
            Messages.failed();

        }
    }

    // creating new directory
    public static void createFolder() {
        File file = new File(readPath());
        if (file.mkdir()) {
            Messages.success();
        } else {
            Messages.failed();
        }
    }

    // searching file in selected directory
    public static void findFile() {
        File directory = new File(readPath());
        if (directory.exists()) {
            System.out.print("Enter the name of file: ");
            String name = scanner.nextLine();
            String fileName = directory.getAbsolutePath() + "\\" + name;
            File file = new File(fileName);
            if (file.exists()) {
                System.out.println("This folder contains your file!");
            } else {
                System.out.println("This folder don't contains your file!");
            }
            Messages.success();
        } else {
            System.out.println("No directory found!");
            Messages.failed();
        }
    }

    // counting selected word in file
    public static void searchInFile() throws IOException {
        File file = new File(readPath());
        if (file.exists() && file.isFile()) {
            System.out.print("Enter the word tot search: ");
            String substr = scanner.nextLine();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            long count = Arrays.stream(result.toString().split("\\s"))
                    .filter(word -> word.contains(substr))
                    .count();
            System.out.println("The number of occurrences of the entered substring in the file: " + count);
            fr.close();
        } else {
            System.out.println("This is a folder or file don't exists");
            Messages.failed();
        }
    }
}
