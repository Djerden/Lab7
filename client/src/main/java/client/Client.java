package client;

import application.Application;
import application.CommandFactory;
import client_commands.ExitCommand;
import client_commands.HelpCommand;
import client_commands.HistoryCommand;
import client_commands.ScriptCommand;
import commands.Command;
import exceptions.AbsenceArgumentException;
import exceptions.UnknownCommandException;
import command_reader.CommandReader;
import io.ConsoleCommandReader;
import io.ConsoleWriter;
import io.Writer;
import network.*;
import data.Response;
import java.io.*;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Client implementation of the Application interface
 */
public class Client implements Application, HistoryFunction {

    private final String address;
    private final int port;
    private boolean isRunning;
    private Command command;
    private Queue<Command> listOfLastCommands;
    private Writer writer;

    private CommandFactory commandFactory;
    private CommandReader commandReader;

    // network
    private ConnectionManager connectionManager = new ConnectionManagerImpl();
    private RequestSender requestSender = new RequestSenderImpl();
    private ResponseReader responseReader = new ResponseReaderImpl();

    public Client(String address, int port) {
        this.address = address;
        this.port = port;

        writer = new ConsoleWriter();

        commandFactory = new CommandSimpleFactory(this, writer, this);
        commandReader = new ConsoleCommandReader(commandFactory, writer);
        // список для команды history
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
        addCommandToHistory(command);
        if (command instanceof HelpCommand || command instanceof ExitCommand || command instanceof HistoryCommand || command instanceof ScriptCommand) {
            command.execute();
        } else {
            sendCommandToServer(command);
        }
    }

    private void sendCommandToServer(Command command) {
        // реализация
        try {
            SocketChannel socketChannel = connectionManager.openConnection(String.valueOf(address), port);
            requestSender.initOutputStream(socketChannel);
            requestSender.sendRequest(command, socketChannel);
            Response response = responseReader.getResponse(socketChannel);
            writer.write(response.getMessage());
            socketChannel.close();
        } catch (IOException e) {
            writer.write("Проблема с подключением");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка с классом");
        }
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
