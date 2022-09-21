package commands;

import collection.PersonCollection;
import io.Writer;

/**
 * A command that allows you to save a collection to a file
 */
public class SaveCollectionCommand implements Command{

    private PersonCollection personCollection;
    private Writer writer;

    public SaveCollectionCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        personCollection.save();
        writer.write("Коллекция сохранена");
    }
}
