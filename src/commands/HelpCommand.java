package commands;

import core.CommandManager;

public class HelpCommand implements Command {
    private final String name = "help";
    private final String description = "Displays this help command.";

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(CommandManager commandManager) {
        Command[] commands = commandManager.getCommands();

        System.out.println("Available commands:");
        for (Command command : commands) {
            System.out.println("- " + command.getName() + " - " + command.getDescription());
        }
    }
}
