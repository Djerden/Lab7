package commands;

import collection.PersonCollection;
import commands.Command;

public class ClearCommand implements Command {

    private PersonCollection personCollection;

    public ClearCommand(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute() {
        personCollection.clear();
    }
}
