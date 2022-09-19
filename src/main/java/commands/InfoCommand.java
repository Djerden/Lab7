package commands;

import collection.PersonCollection;
import io.Writer;

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
}
