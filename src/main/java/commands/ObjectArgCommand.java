package commands;

import io.CommandReader;
import io.Writer;

public interface ObjectArgCommand extends SimpleArgCommand {
    void setNeededObjects(CommandReader reader);
}
