package commands;

import collection.PersonCollection;
import command_reader.CommandReader;
import person.Person;
import user.Auth;

/**
 * A command that allows you to delete objects whose values have more parameters than the specified object
 */
public class RemoveGreaterCommand implements ObjectArgCommand {
    private String result = null;
    private PersonCollection personCollection;
    private Person person;
    private Auth auth;

    public RemoveGreaterCommand() {
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    @Override
    public void execute() {
        personCollection.remove_greater(person);
        result = "Objects exceeding the specified one have been deleted";
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
