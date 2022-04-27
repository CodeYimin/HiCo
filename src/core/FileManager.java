package core;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

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

    public void writeLines(String[] lines) throws Exception {
        PrintWriter writer = new PrintWriter(file);
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
    }

    public String[] readLines() throws Exception {
        int numLines = getNumLines();
        String[] lines = new String[numLines];

        Scanner scanner = new Scanner(file);
        for (int i = 0; i < numLines; i++) {
            lines[i] = scanner.nextLine();
        }
        scanner.close();

        return lines;
    }

    public void addLine(String line) throws Exception {
        final boolean APPEND = true;
        FileWriter fileWriter = new FileWriter(file, APPEND);
        PrintWriter writer = new PrintWriter(fileWriter);
        writer.println(line);
        writer.close();
    }
}
