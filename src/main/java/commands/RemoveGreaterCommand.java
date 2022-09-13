package commands;

import collection.PersonCollection;
import io.CommandReader;
import person.Person;

public class RemoveGreaterCommand implements ObjectArgCommand {

    private PersonCollection personCollection;
    private Person person;

    public RemoveGreaterCommand(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute() {
        personCollection.remove_greater(person);
    }

    @Override
    public void setNeededObjects(CommandReader reader) {
        person = reader.readPerson();
    }

    @Override
    public void setSimpleArg(String str) {

    }
}
