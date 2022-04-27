package commands;

import core.CommandManager;

public class HelpCommand extends Command {
    public HelpCommand(String name, String description) {
        super(name, description);
    }

    public void execute(CommandManager commandManager) {
        Command[] commands = commandManager.getCommands();

        System.out.println("Available commands:");
        for (Command command : commands) {
            System.out.println("- " + command.getName() + " - " + command.getDescription());
        }
    }
}
