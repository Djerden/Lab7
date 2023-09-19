package commands;

import collection.PersonCollection;
import command_reader.CommandReader;
import exceptions.AbsenceArgumentException;
import person.Person;
import user.Auth;

/**
 * Command to add objects of the Person type to the collection
 */
public class InsertCommand implements ObjectArgCommand {

    private PersonCollection personCollection;
    private String number;
    private Person person;

    private Auth auth = null;

    private String result = null;


    public InsertCommand() {
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    @Override
    public void execute() {
        if (auth != null) {
            person.setLogin(auth.getLogin());
        }
        personCollection.insert(number, person);
        result = "Person added";
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
        if (str != null) {
            number = str;
        } else {
            throw new AbsenceArgumentException("For this command, you need to pass an argument - number");
        }
    }

    @Override
    public String toString() {
        return "insert";
    }
}
