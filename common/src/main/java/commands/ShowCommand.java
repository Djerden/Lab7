package commands;

import collection.PersonCollection;
import person.Person;

import java.util.List;

/**
 * A command that outputs all objects in the collection
 */
public class ShowCommand implements Command {
    private String result = "";
    private PersonCollection personCollection;

    public ShowCommand() {
    }

    @Override
    public void execute() {

        List<Person> tempList = personCollection.show();
        if (tempList.isEmpty()) {
            result = "Коллекция пуста";
        } else {
            for (Person i : tempList) {
                result = result + i.toString() + "\n";
            }
        }

    }

    @Override
    public void setCollection(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "show";
    }
}
