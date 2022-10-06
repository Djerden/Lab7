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

        // доступные команды
        // можно изменить текст на command.toString();
        commands.put("exit", new ExitCommand(application)); // клиентская команда
        commands.put("help", new HelpCommand(writer)); // клиентская команда
        commands.put("history", new HistoryCommand(history, writer)); // клиентская команда
        commands.put("execute_script", new ScriptCommand(this, writer)); // клиентская команда

        commands.put("info", new InfoCommand()); // серверная команда
        commands.put("show", new ShowCommand()); // серверная команда
        commands.put("insert", new InsertCommand()); // серверная команда
        commands.put("update", new UpdateByIdCommand()); // серверная команда
        commands.put("remove_key", new RemoveByKeyCommand()); // серверная команда
        commands.put("clear", new ClearCommand()); // серверная команда
        commands.put("remove_greater", new RemoveGreaterCommand()); // серверная команда
        commands.put("remove_greater_key", new RemoveGreaterByKeyCommand()); // серверная команда
        commands.put("max_by_weight", new MaxByWeightCommand()); // серверная команда
        commands.put("filter_less_than_passport_id", new FilterLessThanPassportIdCommand()); // серверная команда
        commands.put("remove_any_by_nationality", new RemoveAnyByNationalityCommand()); // серверная команда
    }

    @Override
    public Command chooseCommand(String nameCommand) {
        return commands.get(nameCommand);
    }
}
