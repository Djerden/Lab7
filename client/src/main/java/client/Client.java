package client;

import application.Application;
import application.CommandFactory;
import client_commands.ExitCommand;
import client_commands.HelpCommand;
import client_commands.HistoryCommand;
import client_commands.ScriptCommand;
import commands.Command;
import commands.UserCommand;
import exceptions.AbsenceArgumentException;
import exceptions.UnknownCommandException;
import command_reader.CommandReader;
import io.ConsoleCommandReader;
import io.ConsoleWriter;
import io.Writer;
import network.*;
import data.Response;
import user.Auth;

import java.io.*;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;
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

    // authorizaion
    private boolean isLogin;
    private Auth auth;


    public Client(String address, int port) {
        this.address = address;
        this.port = port;

        writer = new ConsoleWriter();

        commandFactory = new CommandSimpleFactory(this, writer, this);
        commandReader = new ConsoleCommandReader(commandFactory, writer);
        // list to history
        listOfLastCommands = new LinkedList<>();
    }

    @Override
    public void start() {
        isRunning = true;
        isLogin = false;
        while (!isLogin) {
            try {
                System.out.println("You need login. Enter command \"login\"");
                login();
            } catch (IOException | NullPointerException e) {
                System.out.println("Problems with auth");
            } catch (UnknownCommandException uce) {
                writer.write("Try again");
            }
        }
        writer.write("For reference, use the command \"help\"");
        while(isRunning) {
            try {
                findAndExecuteCommand();
            } catch (IOException e) {
                writer.write("Something with input/output...");
            } catch (UnknownCommandException e) {
                writer.write("Try again");
            } catch (NullPointerException e) {
                writer.write("Input error");
            } catch (AbsenceArgumentException e) {
                writer.write("This command assumes the presence of an argument");
            }
        }
    }

    private void findAndExecuteCommand() throws IOException {
        command = commandReader.readCommands();
        command.setAuth(auth);
        addCommandToHistory(command);

        if (command instanceof UserCommand) {
            Response response = authorized();
            assert response != null;
            if (response.getMessage().equals("success")) {
                isLogin = true;
                auth = new Auth(((UserCommand) command).getLogin(), ((UserCommand) command).getPassword());
            }
            writer.write(response.getMessage());
        }
        else if (command instanceof HelpCommand || command instanceof ExitCommand || command instanceof HistoryCommand) {
            command.execute();

        } else if (command instanceof ScriptCommand) {
            try {
                command.execute();
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
            List<Command> commandList = ((ScriptCommand)command).getCommandList();
            for (Command scriptCommand : commandList) {

                if (scriptCommand instanceof HelpCommand || scriptCommand instanceof ExitCommand || scriptCommand instanceof HistoryCommand) {
                    scriptCommand.execute();
                } else {
                    scriptCommand.setAuth(auth);
                    sendCommandToServer(scriptCommand);
                }
            }

        } else {
            sendCommandToServer(command);
        }
    }

    private void login() throws IOException {
        command = commandReader.readCommands();
        if (command instanceof UserCommand) {
            Response response = authorized();
            assert response != null;
            if (response.getMessage().equals("success")) {
                isLogin = true;
                auth = new Auth(((UserCommand) command).getLogin(), ((UserCommand) command).getPassword());
            }
            writer.write(response.getMessage());
        } else {
            System.out.println("You are not authorized");
        }
    }

    private Response authorized() {
        try {
            SocketChannel socketChannel = connectionManager.openConnection(address, port);
            Auth auth = new Auth("auth request", "request");
            requestSender.initOutputStream(socketChannel);
            requestSender.sendRequest(command, socketChannel);
            Response response = responseReader.getResponse(socketChannel);
            socketChannel.close();
            return response;
        } catch (IOException | ClassNotFoundException ioe) {
            System.out.println("System error with auth");
            return null;
        }
    }



    private void sendCommandToServer(Command command) {
        // realisation
        try {
            SocketChannel socketChannel = connectionManager.openConnection(String.valueOf(address), port);
            requestSender.initOutputStream(socketChannel);
            requestSender.sendRequest(command, socketChannel);
            Response response = responseReader.getResponse(socketChannel);
            writer.write(response.getMessage());
            socketChannel.close();
        } catch (IOException e) {
            writer.write("Connection problem");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error with the class");
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
