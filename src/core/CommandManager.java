package core;

import java.util.Scanner;

import commands.Command;

/**
 * The command manager is responsible for listening to keyboard command input
 * and running the corresponding command on input.
 */
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

    /**
     * Execute the command that matches the given name
     * 
     * @param commandName
     *            name of the command to execute.
     * @return true if the command was found and executed, false otherwise.
     */
    public boolean executeCommand(String commandName) {
        for (Command command : commands) {
            if (command.getName().equalsIgnoreCase(commandName)) {
                command.execute(this);
                return true;
            }
        }
        return false;
    }

    /**
     * Start listening for commands. The command manager will
     * ask for input commands and execute the corresponding command
     * until listening is stopped.
     */
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

    /**
     * Stop listening for commands.
     */
    public void stopListening() {
        listening = false;
    }
}
