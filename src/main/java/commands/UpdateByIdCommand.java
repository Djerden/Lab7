package commands;

import collection.PersonCollection;
import io.CommandReader;
import person.Person;

public class UpdateByIdCommand implements ObjectArgCommand {
    private PersonCollection personCollection;
    private int id;
    private Person person;

    public UpdateByIdCommand(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute() {
        personCollection.update(id, person);
    }

    @Override
    public void setNeededObjects(CommandReader reader) {
        person = reader.readPerson();
    }

    @Override
    public void setSimpleArg(String str) {
        id = Integer.valueOf(str);
    }
}
