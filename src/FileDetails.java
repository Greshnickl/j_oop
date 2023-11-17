import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Arrays;

public class FileDetails {
    public static void printDetails(String filePath) {
        File file = new File(filePath);
        
        if (!file.exists() || !file.isFile()) {
            System.out.println("File does not exist or is not a regular file.");
            return;
        }

        System.out.println("File Name: " + file.getName());
        System.out.println("Size: " + file.length() + " bytes");

        String extension = getFileExtension(file);

        try {
            switch (extension) {
                case "txt":
                    printTextFileDetails(file);
                    break;
                case "png":
                case "jpg":
                    break;
                case "py":
                case "java":
                    printProgramFileDetails(file);
                    break;
                default:
                    System.out.println("No specific details available for this file type.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void printTextFileDetails(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());    
        long wordCount = lines.stream()
        .flatMap(line -> Arrays.stream(line.split("\\s+")))
        .count();
        long charCount = lines.stream()
                          .mapToLong(String::length)
                          .sum();
              System.out.println("Line Count: " + lines.size());
              System.out.println("Word Count: " + wordCount);
              System.out.println("Character Count: " + charCount);
    }

    private static void printProgramFileDetails(File file) throws IOException {
        printTextFileDetails(file);
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; 
        }
        return fileName.substring(lastIndexOf + 1);
    }
}
