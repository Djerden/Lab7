package commands;

import collection.PersonCollection;

/**
 * A command that allows you to delete objects whose keys are larger than the specified one
 */
public class RemoveGreaterByKeyCommand implements SimpleArgCommand{
    private String result = null;
    private PersonCollection personCollection;
    private String number;

    public RemoveGreaterByKeyCommand() {
    }

    @Override
    public void execute() {
        personCollection.remove_greater_key(number);
        result = "ключи, номера которых превышали заданный, удалены";
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
