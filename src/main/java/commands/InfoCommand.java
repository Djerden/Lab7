package commands;

import collection.PersonCollection;
import io.Writer;

/**
 * Command that outputs information about the collection
 */
public class InfoCommand implements Command {

    private PersonCollection personCollection;
    private Writer writer;

    public InfoCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        writer.write(personCollection.info());
    }

    @Override
    public String toString() {
        return "info";
    }
}
