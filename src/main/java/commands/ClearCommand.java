package commands;

import collection.PersonCollection;
import commands.Command;
import io.Writer;

/**
 * Command to clear the collection
 */
public class ClearCommand implements Command {

    private PersonCollection personCollection;

    private Writer writer;
    public ClearCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        personCollection.clear();
        writer.write("Коллекция очищена");
    }
}
