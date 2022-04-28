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

    private void createFileIfNotExist() {
        if (file.exists()) {
            return;
        }

        try {
            file.createNewFile();
        } catch (Exception e) {
            System.out.println("File is missing and failed to create file.");
        }
    }

    public void writeLines(String[] lines) throws Exception {
        createFileIfNotExist();
        PrintWriter writer = new PrintWriter(file);
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
    }

    public String[] readLines() throws Exception {
        createFileIfNotExist();
        String[] lines = {};

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines = ArrayUtils.concat(lines, line);
        }
        scanner.close();

        return lines;
    }

    public void addLine(String line) throws Exception {
        final boolean APPEND = true;
        createFileIfNotExist();
        FileWriter fileWriter = new FileWriter(file, APPEND);
        PrintWriter writer = new PrintWriter(fileWriter);
        writer.println(line);
        writer.close();
    }
}
