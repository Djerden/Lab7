package commands;

import collection.PersonCollection;
import io.Writer;

/**
 * A command that allows you to upload saved data to a collection
 */
public class LoadCommand implements Command{

    private PersonCollection personCollection;
    private Writer writer;

    public LoadCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        personCollection.loadData();
        writer.write("Данные успешно загружены");
    }
}