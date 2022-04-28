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

    public abstract void execute(CommandManager commandManager);
}
