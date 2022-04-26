package commands;

import core.CommandManager;

public class ExitCommand implements Command {
    private final String name = "exit";
    private final String description = "Exit the program.";

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(CommandManager commandManager) {
        System.out.println("Exiting...");
        commandManager.stopListening();
    }
}
