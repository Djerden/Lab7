package commands;

import io.CommandReader;
import io.Writer;

/**
 * Interface for commands with complex arguments
 */
public interface ObjectArgCommand extends SimpleArgCommand {
    void setNeededObjects(CommandReader reader);
}
