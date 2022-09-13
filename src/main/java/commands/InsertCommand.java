package commands;

import collection.PersonCollection;
import io.CommandReader;
import person.Person;

public class InsertCommand implements ObjectArgCommand {

    private PersonCollection personCollection;
    private String number;
    private Person person;

    public InsertCommand(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute() {
        personCollection.insert(number, person);
    }

    @Override
    public void setNeededObjects(CommandReader reader) {
        person = reader.readPerson();
    }

    @Override
    public void setSimpleArg(String str) {
        number = str;
    }
}
