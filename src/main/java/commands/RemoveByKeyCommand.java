package commands;

import collection.PersonCollection;

public class RemoveByKeyCommand implements SimpleArgCommand {

    private PersonCollection personCollection;
    private String number;

    public RemoveByKeyCommand(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute() {
        personCollection.remove_key(number);
    }

    @Override
    public void setSimpleArg(String str) {
        number = str;
    }
}
