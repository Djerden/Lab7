package commands;

import command_reader.CommandReader;

/**
 * Interface for commands with complex arguments
 */
public interface ObjectArgCommand extends SimpleArgCommand {
    void setNeededObjects(CommandReader reader);
}
