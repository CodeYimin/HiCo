package commands;

import core.CommandManager;

public interface Command {
    public String getName();

    public String getDescription();

    public void execute(CommandManager commandManager);
}
