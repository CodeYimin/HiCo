package core;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import utils.ArrayUtils;

public class FileManager {
    private final File file;

    public FileManager(File file) {
        this.file = file;
    }

    private int getNumLines() {
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

    private void writeLines(String[] lines) {
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

    public String[] readLines() {
        int numLines = getNumLines();
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

    public void addLine(String line) {
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

    public boolean includesLine(String line) {
        String[] lines = readLines();
        return ArrayUtils.includes(lines, line);
    }

    public boolean removeLine(String line) {
        if (!includesLine(line)) {
            return false;
        }

        String[] lines = readLines();
        String[] newLines = ArrayUtils.withElementRemoved(lines, line);
        writeLines(newLines);
        return true;
    }
}
