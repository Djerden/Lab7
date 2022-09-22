package commands;

import collection.PersonCollection;
import io.Writer;

/**
 * A command that allows you to delete objects whose keys are larger than the specified one
 */
public class RemoveGreaterByKeyCommand implements SimpleArgCommand{

    private PersonCollection personCollection;
    private Writer writer;
    private String number;

    public RemoveGreaterByKeyCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        personCollection.remove_greater_key(number);
        writer.write("Все элементы, ключ которых превышает заданный удалены");
    }

    @Override
    public void setSimpleArg(String str) {
        number = str;
    }

    @Override
    public String toString() {
        return "remove_greater_key";
    }
}
