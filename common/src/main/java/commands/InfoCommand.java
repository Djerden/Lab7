package commands;

import collection.PersonCollection;


/**
 * Command that outputs information about the collection
 */
public class InfoCommand implements Command {
    private String result = null;
    private PersonCollection personCollection;

    public InfoCommand() {
    }

    @Override
    public void execute() {
        result = personCollection.info();
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
        return "info";
    }
}
