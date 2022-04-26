package utils;

import java.util.Scanner;

public class InputUtils {
    public static boolean isInt(String input) {
        String digits = "0123456789";

        if (input.length() == 0) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (digits.indexOf(c) == -1) {
                return false;
            }
        }

        return true;
    }

    public static boolean isDouble(String input) {
        String digits = "0123456789.";
        int numDecimalPoints = 0;

        if (input.length() == 0) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (digits.indexOf(c) == -1) {
                return false;
            } else if (c == '.') {
                numDecimalPoints++;
                if (numDecimalPoints > 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isValidChoice(String response, String[] choices) {
        for (String choice : choices) {
            if (choice.equals(response)) {
                return true;
            }
        }
        return false;
    }

    public static String promptString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        String response = keyboard.nextLine().toLowerCase();
        return response;
    }

    public static String promptString(Scanner keyboard, String prompt, String[] choices) {
        String response = promptString(keyboard, prompt);
        while (!isValidChoice(response, choices)) {
            System.out.println("Invalid choice. Please enter one of the following: ");
            for (String choice : choices) {
                System.out.println("\t- " + choice);
            }
            response = promptString(keyboard, prompt);
        }
        return response;
    }

    public static int promptInt(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        String response = keyboard.nextLine();
        while (!isInt(response)) {
            System.out.println("Invalid input. Please enter an integer.");
            response = promptString(keyboard, prompt);
        }
        return Integer.parseInt(response);
    }

    public static int promptInt(Scanner keyboard, String prompt, int min) {
        int response = promptInt(keyboard, prompt);
        while (response < min) {
            System.out.println("Invalid choice. Please enter a number above " + min);
            response = promptInt(keyboard, prompt);
        }
        return response;
    }

    public static double promptDouble(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        String response = keyboard.nextLine();
        while (!isDouble(response)) {
            System.out.println("Invalid input. Please enter a double.");
            response = promptString(keyboard, prompt);
        }
        return Double.parseDouble(response);
    }

    public static double promptDouble(Scanner keyboard, String prompt, double min) {
        double response = promptDouble(keyboard, prompt);
        while (response < min) {
            System.out.println("Invalid choice. Please enter a number above " + min);
            response = promptDouble(keyboard, prompt);
        }
        return response;
    }
}
