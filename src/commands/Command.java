package commands;

import core.CommandManager;

/**
 * The base class for all commands.
 */
public abstract class Command {
    private final String name;
    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    };

    public String getDescription() {
        return description;
    };

    /**
     * This method is called when the command is executed.
     *
     * @param commandManager
     *            The command manager that is executing the command.
     */
    public abstract void execute(CommandManager commandManager);
}
