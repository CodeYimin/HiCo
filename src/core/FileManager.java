package core;

import java.io.File;
import java.io.FileNotFoundException;
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
        try {
            file.createNewFile();
        } catch (Exception e) {
            System.out.println("Failed to create file.");
        }
    }

    public void writeLines(String[] lines) throws Exception {
        try {
            PrintWriter writer = new PrintWriter(file);
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (FileNotFoundException error) {
            createFileIfNotExist();
            writeLines(lines);
        } catch (Exception error) {
            throw error;
        }
    }

    public String[] readLines() throws Exception {
        try {
            String[] lines = {};

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines = ArrayUtils.concat(lines, line);
            }
            scanner.close();

            return lines;
        } catch (FileNotFoundException error) {
            createFileIfNotExist();
            return readLines();
        } catch (Exception error) {
            throw error;
        }
    }

    public void addLine(String line) throws Exception {
        final boolean APPEND = true;
        FileWriter fileWriter = new FileWriter(file, APPEND);
        PrintWriter writer = new PrintWriter(fileWriter);
        writer.println(line);
        writer.close();
    }
}
