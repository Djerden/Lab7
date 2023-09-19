package client;

import application.Application;
import application.CommandFactory;
import client_commands.ExitCommand;
import client_commands.HelpCommand;
import client_commands.HistoryCommand;
import client_commands.ScriptCommand;
import commands.*;
import io.Writer;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory that creates the commands necessary for the operation of this application
 */
public class CommandSimpleFactory implements CommandFactory {
    private Map<String, Command> commands;

    private Application application;
    private HistoryFunction history;
    private Writer writer;

    public CommandSimpleFactory(Application application, Writer writer, HistoryFunction history) {
        this.application = application;
        this.history = history;
        this.writer = writer;
        commands = new HashMap<>();
        setCommands();
    }
    private void setCommands() {


        commands.put("exit", new ExitCommand(application));
        commands.put("login", new UserCommand());
        commands.put("help", new HelpCommand(writer));
        commands.put("history", new HistoryCommand(history, writer));
        commands.put("execute_script", new ScriptCommand(this, writer));

        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("insert", new InsertCommand());
        commands.put("update", new UpdateByIdCommand());
        commands.put("remove_key", new RemoveByKeyCommand());
        commands.put("clear", new ClearCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("remove_greater_key", new RemoveGreaterByKeyCommand());
        commands.put("max_by_weight", new MaxByWeightCommand());
        commands.put("filter_less_than_passport_id", new FilterLessThanPassportIdCommand());
        commands.put("remove_any_by_nationality", new RemoveAnyByNationalityCommand());

    }

    @Override
    public Command chooseCommand(String nameCommand) {
        return commands.get(nameCommand);
    }
}
