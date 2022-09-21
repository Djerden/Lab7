package client;

import collection.PersonCollection;
import commands.*;
import io.Writer;
import person.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory that creates the commands necessary for the operation of this application
 */
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
        commands.put("history", new HistoryCommand(application, writer));
        commands.put("info", new InfoCommand(personCollection, writer));
        commands.put("show", new ShowCommand(personCollection, writer));
        commands.put("insert", new InsertCommand(personCollection, writer));
        commands.put("update", new UpdateByIdCommand(personCollection, writer));
        commands.put("remove_key", new RemoveByKeyCommand(personCollection, writer));
        commands.put("clear", new ClearCommand(personCollection, writer));
        commands.put("save", new SaveCollectionCommand(personCollection, writer));
        commands.put("remove_greater", new RemoveGreaterCommand(personCollection));
        commands.put("remove_greater_key", new RemoveGreaterByKeyCommand(personCollection, writer));
        commands.put("max_by_weight", new MaxByWeightCommand(personCollection, writer));
        commands.put("filter_less_than_passport_id", new FilterLessThanPassportIdCommand(personCollection, writer));
        commands.put("load", new LoadCommand(personCollection, writer));
        commands.put("remove_any_by_nationality", new RemoveAnyByNationalityCommand(personCollection, writer));
    }

    @Override
    public Command chooseCommand(String nameCommand) {
        return commands.get(nameCommand);
    }
}
