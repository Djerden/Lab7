package server_commands;

import application.Application;
import application.CommandFactory;
import collection.PersonCollection;
import commands.Command;

import java.util.HashMap;
import java.util.Map;

public class ServerCommandSimpleFactory {

    private Map<String, ServerCommand> commands;
    private Application application;
    private PersonCollection personCollection;

    public ServerCommandSimpleFactory(Application application, PersonCollection personCollection) {
        this.application = application;
        this.personCollection = personCollection;
        commands = new HashMap<>();
        setServerCommands();
    }


    public ServerCommand chooseCommand(String nameCommand) {
        return commands.get(nameCommand);
    }

    private void setServerCommands() {
        commands.put("exit", new ExitCommand(application));
        commands.put("save", new SaveCommand(personCollection));
    }
}
