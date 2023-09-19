package commands;

import collection.PersonCollection;
import user.Auth;

/**
 * A command that allows you to delete objects whose keys are larger than the specified one
 */
public class RemoveGreaterByKeyCommand implements SimpleArgCommand{
    private String result = null;
    private PersonCollection personCollection;
    private String number;
    private Auth auth;

    public RemoveGreaterByKeyCommand() {
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    @Override
    public void execute() {
        personCollection.remove_greater_key(number);
        result = "keys whose numbers exceeded the specified one have been deleted";
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
    public void setSimpleArg(String str) {
        number = str;
    }

    @Override
    public String toString() {
        return "remove_greater_key";
    }
}
