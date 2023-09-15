package commands;

import collection.PersonCollection;
import command_reader.CommandReader;
import exceptions.AbsenceArgumentException;
import person.Person;
/**
 * Command to add objects of the Person type to the collection
 */
public class InsertCommand implements ObjectArgCommand {

    private PersonCollection personCollection;
    private String number;
    private Person person;

    private String result = null;


    public InsertCommand() {
    }

    @Override
    public void execute() {
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
