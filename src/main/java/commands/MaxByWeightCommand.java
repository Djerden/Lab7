package commands;

import collection.PersonCollection;
import io.Writer;

public class MaxByWeightCommand implements Command{

    private PersonCollection personCollection;
    private Writer writer;

    public MaxByWeightCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        writer.write(personCollection.max_by_weight().toString());
    }
}