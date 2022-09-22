package commands;

import collection.PersonCollection;
import io.Writer;

/**
 * The command that outputs the object with the maximum weight
 */
public class MaxByWeightCommand implements Command{

    private PersonCollection personCollection;
    private Writer writer;

    public MaxByWeightCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        try {
        writer.write(personCollection.max_by_weight().toString());
        } catch (NullPointerException e) {
            writer.write("Коллекция пуста");
        }
    }

    @Override
    public String toString() {
        return "max_by_weight";
    }
}
