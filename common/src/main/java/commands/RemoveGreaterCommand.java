package commands;

import collection.PersonCollection;
import command_reader.CommandReader;
import person.Person;

/**
 * A command that allows you to delete objects whose values have more parameters than the specified object
 */
public class RemoveGreaterCommand implements ObjectArgCommand {
    private String result = null;
    private PersonCollection personCollection;
    private Person person;

    public RemoveGreaterCommand() {
    }

    @Override
    public void execute() {
        personCollection.remove_greater(person);
        result = "Объекты, превышающие заданный, удалены";
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
    public void setNeededObjects(CommandReader reader) {
        person = reader.readPerson();
    }

    @Override
    public void setSimpleArg(String str) {

    }
    @Override
    public String toString() {
        return "remove_greater";
    }
}
