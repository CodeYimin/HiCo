package core;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import utils.ArrayUtils;

/**
 * The file manager is responsible for providing functionality
 * for easily saving and loading data from files.
 */
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

    /**
     * Overrides the entire file with new lines.
     * 
     * @param lines
     *            lines to override the file with
     * @throws Exception
     */
    public void writeLines(String[] lines) throws Exception {
        createFileIfNotExist();
        PrintWriter writer = new PrintWriter(file);
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
    }

    /**
     * Read all lines from the file.
     * 
     * @return all lines from the file
     * @throws Exception
     */
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

    /**
     * Appends a new line to the end of the file without overriding the file.
     * 
     * @param line
     *            the line to append
     * @throws Exception
     */
    public void addLine(String line) throws Exception {
        final boolean APPEND = true;
        createFileIfNotExist();
        FileWriter fileWriter = new FileWriter(file, APPEND);
        PrintWriter writer = new PrintWriter(fileWriter);
        writer.println(line);
        writer.close();
    }
}
