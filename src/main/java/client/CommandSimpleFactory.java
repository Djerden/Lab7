package client;

import collection.PersonCollection;
import commands.Command;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.ShowCommand;
import io.Writer;
import person.Person;

import java.util.HashMap;
import java.util.Map;

public class CommandSimpleFactory implements CommandFactory{
    private Map<String, Command> commands;

    private PersonCollection personCollection;
    private Application application;
    private Writer writer;

    public CommandSimpleFactory(PersonCollection personCollection, Application application, Writer writer) {
        this.personCollection = personCollection;
        this.application = application;
        this.writer = writer;
        commands = new HashMap<>();
        setCommands();
    }
    private void setCommands() {

        // доступные команды

        commands.put("exit", new ExitCommand(application));
        commands.put("help", new HelpCommand(writer));
        commands.put("show", new ShowCommand((PersonCollection) personCollection, writer));
    }

    @Override
    public Command chooseCommand(String nameCommand) {
        return commands.get(nameCommand);
    }
}
