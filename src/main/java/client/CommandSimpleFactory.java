package client;

import collection.PersonCollection;
import commands.*;
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
        commands.put("show", new ShowCommand(personCollection, writer));
        commands.put("insert", new InsertCommand(personCollection));
        commands.put("update", new UpdateByIdCommand(personCollection));
        commands.put("remove_key", new RemoveByKeyCommand(personCollection));
        commands.put("clear", new ClearCommand(personCollection));
        commands.put("save", new SaveCollectionCommand(personCollection));
        commands.put("remove_greater", new RemoveGreaterCommand(personCollection));
        commands.put("remove_greater_key", new RemoveGreaterByKeyCommand(personCollection));
        commands.put("max_by_weight", new MaxByWeightCommand(personCollection, writer));
        commands.put("filter_less_than_passport_id", new FilterLessThanPassportIdCommand(personCollection, writer));
    }

    @Override
    public Command chooseCommand(String nameCommand) {
        return commands.get(nameCommand);
    }
}
