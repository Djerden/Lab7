package client_commands;

import application.CommandFactory;
import collection.PersonCollection;
import commands.Command;
import commands.SimpleArgCommand;
import exceptions.AbsenceArgumentException;
import exceptions.UnknownCommandException;
import command_reader.CommandReader;
import io.ScriptReader;
import io.Writer;
import user.Auth;

import java.io.File;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScriptCommand implements SimpleArgCommand {

    private String fileName;

    private CommandFactory factory;
    private Writer writer;

    private Scanner scanner;

    private List<Command> commandList = new ArrayList<>();

    public ScriptCommand(CommandFactory factory, Writer writer) {
        this.factory = factory;
        this.writer = writer;
    }

    @Override
    public void setAuth(Auth auth) {

    }

    @Override
    public void execute() {
        File file = new File(fileName);
        if (file.length() == 0) {
            throw new RuntimeException("The file is empty");
        }

        try {
            scanner = new Scanner(file);
            CommandReader commandReader = new ScriptReader(scanner, factory, writer);
            String input;
            while (scanner.hasNextLine()) {
                try {
                    Command command = commandReader.readCommands();
                    commandList.add(command);
                } catch (UnknownCommandException e) {
                    writer.write("Unreadable command");
                } catch (NullPointerException e) {
                    writer.write("Input error");
                } catch (AbsenceArgumentException e) {
                    writer.write("One of the commands assumes the presence of an argument");
                }
            }
        } catch (IOException e) {

        }
    }

    @Override
    public void setCollection(PersonCollection personCollection) {

    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public void setSocketChannel(SocketChannel socketChannel) {

    }

    @Override
    public SocketChannel getSocketChannel() {
        return null;
    }

    @Override
    public void setSimpleArg(String str) {
        fileName = str;
    }

    @Override
    public String toString() {
        return "execute_script";
    }

    public List<Command> getCommandList() {
        return commandList;
    }

}
