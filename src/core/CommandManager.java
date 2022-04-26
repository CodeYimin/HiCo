package core;

import java.util.Scanner;

import commands.Command;
import storage.ProductStorage;

public class CommandManager {
    private final ProductStorage productStorage;
    private final Command[] commands;
    private final Scanner keyboard;
    private boolean listening = false;

    public CommandManager(Command[] commands, ProductStorage productStorage, Scanner keyboard) {
        this.commands = commands;
        this.productStorage = productStorage;
        this.keyboard = keyboard;
    }

    public Command[] getCommands() {
        return this.commands;
    }

    public ProductStorage getProductStorage() {
        return this.productStorage;
    }

    public Scanner getKeyboard() {
        return this.keyboard;
    }

    public boolean isListening() {
        return this.listening;
    }

    public boolean executeCommand(String commandName) {
        for (Command command : commands) {
            if (command.getName().equalsIgnoreCase(commandName)) {
                command.execute(this);
                return true;
            }
        }
        return false;
    }

    public void startListening() {
        listening = true;
        while (listening) {
            System.out.print("> ");
            String commandName = keyboard.nextLine();
            boolean commandExecuted = executeCommand(commandName);
            if (!commandExecuted) {
                System.out.println("Command not found. Type \"help\" to see a list of commands.");
            }
        }
    }

    public void stopListening() {
        listening = false;
    }
}
