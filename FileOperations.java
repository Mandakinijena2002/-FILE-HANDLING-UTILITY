import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileOperations {

    /**
     * Writes content to a file.
     * 
     * @param filePath The path to the file.
     * @param content  The content to write.
     */
    public static void writeFile(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
            System.out.println("Content written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Reads content from a file.
     * 
     * @param filePath The path to the file.
     * @return The content of the file.
     */
    public static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Appends content to a file.
     * 
     * @param filePath The path to the file.
     * @param content  The content to append.
     */
    public static void appendToFile(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(content);
            System.out.println("Content appended to file successfully.");
        } catch (IOException e) {
            System.err.println("Error appending to file: " + e.getMessage());
        }
    }

    /**
     * Modifies content in a file.
     * 
     * @param filePath    The path to the file.
     * @param oldContent  The content to replace.
     * @param newContent  The new content.
     */
    public static void modifyFile(String filePath, String oldContent, String newContent) {
        try {
            String fileContent = readFile(filePath);
            if (fileContent != null) {
                fileContent = fileContent.replace(oldContent, newContent);
                writeFile(filePath, fileContent);
                System.out.println("File content modified successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error modifying file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the file path:");
        String filePath = scanner.nextLine();

        System.out.println("Enter the content to write:");
        String content = scanner.nextLine();

        writeFile(filePath, content);

        System.out.println("File content:");
        System.out.println(readFile(filePath));

        System.out.println("Enter the content to append:");
        content = scanner.nextLine();

        appendToFile(filePath, content);

        System.out.println("File content after appending:");
        System.out.println(readFile(filePath));

        System.out.println("Enter the old content to replace:");
        String oldContent = scanner.nextLine();

        System.out.println("Enter the new content:");
        String newContent = scanner.nextLine();

        modifyFile(filePath, oldContent, newContent);

        System.out.println("File content after modification:");
        System.out.println(readFile(filePath));

        scanner.close();
    }
}
