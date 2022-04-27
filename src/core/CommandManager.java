package core;

import java.util.Scanner;

import commands.Command;

public class CommandManager {
    private final Command[] commands;
    private final Scanner keyboard;
    private boolean listening = false;

    public CommandManager(Command[] commands, Scanner keyboard) {
        this.commands = commands;
        this.keyboard = keyboard;
    }

    public Command[] getCommands() {
        return this.commands;
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
