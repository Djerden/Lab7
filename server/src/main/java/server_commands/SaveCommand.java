package server_commands;

import collection.PersonCollection;

public class SaveCommand implements ServerCommand{

    private PersonCollection personCollection;

    public SaveCommand(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute() {
        //personCollection.save();
        log.Logback.getLogger().info("collection was saved");
    }
}
