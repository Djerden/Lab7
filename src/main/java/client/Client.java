package client;

import collection.*;
import commands.Command;
import exceptions.AbsenceArgumentException;
import exceptions.NoInputException;
import exceptions.UnknownCommandException;
import io.CommandReader;
import io.ConsoleCommandReader;
import io.ConsoleWriter;
import io.Writer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Client implements Application {
    private String fileName;
    private boolean isRunning;
    private Command command;
    private Queue<Command> listOfLastCommands;
    private Writer writer;

    private PersonReader personReader;
    private PersonWriter personWriter;
    private PersonCollection personCollection;

    private CommandFactory commandFactory;
    private CommandReader commandReader;

    public Client(String fileName) {
        this.fileName = fileName;
        writer = new ConsoleWriter();

        personReader = new JsonPersonReader(fileName);
        personWriter = new JsonPersonWriter(fileName);
        personCollection = new HashMapPersonCollection(personReader, personWriter);

        commandFactory = new CommandSimpleFactory(personCollection, this, writer);
        commandReader = new ConsoleCommandReader(commandFactory, writer);
        listOfLastCommands = new LinkedList<>();
    }

    @Override
    public void start() {
        isRunning = true;
        writer.write("Для справки воспользуйтесь командой \"help\"");
        while(isRunning) {
            try {
                findAndExecuteCommand();
            } catch (IOException e) {
                writer.write("Что-то с вводом/выводом...");
            } catch (UnknownCommandException e) {
                writer.write("Попробуйте еще раз");
            } catch (NullPointerException e) {
                writer.write("Ошибка ввода");
            } catch (AbsenceArgumentException e) {
                writer.write("Эта команда предполагает наличие аргумента");
            }
        }
    }

    private void findAndExecuteCommand() throws IOException {
        command = commandReader.readCommands();
        command.execute();
        addCommandToHistory(command);
    }
    @Override
    public Queue<Command> getHistory() {
        return listOfLastCommands;
    }
    private void addCommandToHistory(Command com) {
        if (listOfLastCommands.size() >= 9) {
            listOfLastCommands.poll();
        }
        listOfLastCommands.offer(com);
    }

    @Override
    public void exit() {
        isRunning = false;
    }
}
