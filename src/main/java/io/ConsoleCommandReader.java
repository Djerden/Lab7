package io;

import client.CommandFactory;
import client.CommandSimpleFactory;
import commands.Command;
import commands.ObjectArgCommand;
import commands.SimpleArgCommand;
import exceptions.NoInputException;
import exceptions.UnknownCommandException;
import person.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleCommandReader implements CommandReader {
    private Writer writer;
    private CommandFactory factory;
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public ConsoleCommandReader(CommandFactory factory, Writer writer) {
        this.factory = factory;
        this.writer = writer;
    }

    @Override
    public Command readCommands() throws IOException {

        String input = bufferedReader.readLine();
        if (input == null) {
            writer.write("Введите что-то более осмысленное");
            throw new NoInputException("Отсутствие ввода");
        }

        String[] commandParameters = input.trim().toLowerCase().split("\\s+");
        Command command = factory.chooseCommand(commandParameters[0]);
        if (command == null) {
            throw new UnknownCommandException();
        }
        if (command instanceof SimpleArgCommand) {
            if (commandParameters[1] != null) {
                ((ObjectArgCommand)command).setSimpleArg(commandParameters[1]);
            }
        }
        if (command instanceof ObjectArgCommand) {
            ((ObjectArgCommand)command).setNeededObjects(this);
        }

        return command;
    }

    //public Person readPerson() {

    //}
}
