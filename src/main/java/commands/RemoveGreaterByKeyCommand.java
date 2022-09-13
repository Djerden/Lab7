package commands;

import collection.PersonCollection;

public class RemoveGreaterByKeyCommand implements SimpleArgCommand{

    private PersonCollection personCollection;
    private String number;

    public RemoveGreaterByKeyCommand(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute() {
        personCollection.remove_greater_key(number);
    }

    @Override
    public void setSimpleArg(String str) {
        number = str;
    }
}
