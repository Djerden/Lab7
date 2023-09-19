package commands;

import collection.PersonCollection;
import user.Auth;

/**
 * Command to clear the collection
 */
public class ClearCommand implements Command {

    private String result = null;
    private PersonCollection personCollection;
    private Auth auth = null;

    public ClearCommand() {
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    @Override
    public void execute() {
        personCollection.clear(auth.getLogin());
        result = "Your objects have been deleted";
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
        return "clear";
    }
}
