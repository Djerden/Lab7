package commands;

import collection.PersonCollection;

public class SaveCollectionCommand implements Command{

    private PersonCollection personCollection;

    public SaveCollectionCommand(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute() {
        personCollection.save();
    }
}
