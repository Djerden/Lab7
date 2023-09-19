package commands;

import collection.PersonCollection;
import command_reader.CommandReader;
import exceptions.AbsenceArgumentException;
import person.Person;
import user.Auth;

/**
 * A command that replaces another object by id
 */
public class UpdateByIdCommand implements ObjectArgCommand {
    private String result = null;
    private PersonCollection personCollection;
    private Integer id;
    private Person person;
    private Auth auth = null;

    public UpdateByIdCommand() {
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
        personCollection.update(id, person);
        result = "Object with id = " + id + " has been replaced";
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
            id = Integer.valueOf(str);
        } else {
            throw new AbsenceArgumentException("An argument must be passed for this command - id");
        }
    }

    @Override
    public String toString() {
        return "update";
    }
}
