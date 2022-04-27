package commands;

import core.CommandManager;

public class ExitCommand extends Command {
    public ExitCommand(String name, String description) {
        super(name, description);
    }

    public void execute(CommandManager commandManager) {
        System.out.println("Exiting...");
        commandManager.stopListening();
    }
}
