package commands;

import core.CommandManager;

/**
 * Command to make the command manager stop listening for commands.
 */
public class ExitCommand extends Command {
    public ExitCommand(String name, String description) {
        super(name, description);
    }

    public void execute(CommandManager commandManager) {
        System.out.println("Exiting...");
        commandManager.stopListening();
    }
}
