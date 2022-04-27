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

    private int getNumLines() throws Exception {
        int numLines = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            numLines++;
        }
        scanner.close();
        return numLines;
    }

    private boolean writeLines(String[] lines) {
        try {
            PrintWriter writer = new PrintWriter(file);
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public String[] readLines() {
        try {
            int numLines = getNumLines();
            String[] lines = new String[numLines];
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < numLines; i++) {
                lines[i] = scanner.nextLine();
            }
            scanner.close();
            return lines;
        } catch (Exception error) {
            return new String[0];
        }
    }

    public boolean addLine(String line) {
        final boolean APPEND = true;
        try {
            FileWriter fileWriter = new FileWriter(file, APPEND);
            PrintWriter writer = new PrintWriter(fileWriter);
            writer.println(line);
            writer.close();
            return true;
        } catch (Exception error) {
            return false;
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
        if (lines.length == 0) {
            return false;
        }

        lines = ArrayUtils.withoutElement(lines, line);
        return writeLines(lines);
    }
}
