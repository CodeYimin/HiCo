package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileUtils {
    public static int getNumLines(File file, String fileName) {
        int numLines = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                numLines++;
            }
            scanner.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return numLines;
    }

    public static String[] readLines(File file) {
        int numLines = getNumLines(file, file.getName());
        String[] lines = new String[numLines];
        try {
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < numLines; i++) {
                lines[i] = scanner.nextLine();
            }
            scanner.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return lines;
    }

    public static void writeLines(File file, String[] lines) {
        try {
            PrintWriter writer = new PrintWriter(file);
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public static void addLine(File file, String line) {
        final boolean APPEND = true;
        try {
            FileWriter fileWriter = new FileWriter(file, APPEND);
            PrintWriter writer = new PrintWriter(fileWriter);
            writer.println(line);
            writer.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public static boolean removeLine(File file, String line) {
        if (!includesLine(file, line)) {
            return false;
        }

        String[] lines = readLines(file);
        String[] newLines = ArrayUtils.withRemovedElement(lines, line);
        writeLines(file, newLines);
        return true;
    }

    public static boolean includesLine(File file, String line) {
        String[] lines = readLines(file);
        return ArrayUtils.includes(lines, line);
    }
}
