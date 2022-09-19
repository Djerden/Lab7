package commands;

import collection.PersonCollection;
import io.Writer;
import person.Person;

import java.util.List;
import java.util.Map;

public class ShowCommand implements Command {

    private PersonCollection personCollection;
    private Writer writer;

    public ShowCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        List<Person> tempList = personCollection.show();
        if (tempList.isEmpty()) {
            writer.write("Коллекция пуста");
        } else {
            for (Person i : tempList) {
                writer.write(i.toString());
            }
        }
    }
}
