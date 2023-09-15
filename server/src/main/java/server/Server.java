package server;

import application.Application;
import collection.HashMapPersonCollection;
import collection.PersonCollection;
import commands.Command;
import data.Response;
import network.*;
import server_commands.ServerCommand;
import server_commands.ServerCommandSimpleFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server implements Application {

    //private static String dataFileName = System.getenv("DATA_TO_LAB5");

    private final CommandReader commandReader = new CommandReaderImpl();
    private final ResponseWriter responseWriter = new ResponseWriterImpl();
    private final ResponseSender responseSender = new ResponseSenderImpl();
    private final ServerConnectionManager serverConnectionManager = new ServerConnectionManagerImpl();

    private final PersonCollection personCollection = new HashMapPersonCollection(); //new JsonPersonReader(dataFileName), new JsonPersonWriter(dataFileName)

    private ServerCommandSimpleFactory factory = new ServerCommandSimpleFactory(this, personCollection);

    String address = "localhost";
    int port = 8080;

    private boolean isRunning;


    @Override
    public void start() {
//        if (dataFileName == null) {
//            throw new IncorrectFileSettings();
//        }
        consoleStart();
        log.Logback.getLogger().info("server was started");
        isRunning = true;
        Command command;

//        try {
//            personCollection.loadData();
//            log.Logback.getLogger().info("data was parsed");
//        } catch (Exception e) {}

        Selector selector;
        while (isRunning) {
            try {
                serverConnectionManager.openConnection(address, port);
                log.Logback.getLogger().info("connection is open");
                try {
                    selector = serverConnectionManager.listen();
                } catch (ClosedSelectorException e) {
                    log.Logback.getLogger().error("close selector exception");
                    return;
                }

                try {
                    String result;
                    command = commandReader.readCommand(selector);
                    log.Logback.getLogger().info("command was received");
                    command.setCollection(personCollection);

                    command.execute();

                    log.Logback.getLogger().info("command was executed");
                    result = command.getResult();

                    Response response = responseWriter.writeResponse(result);
                    log.Logback.getLogger().info("response was wrote");
                    responseSender.sendResponse(response, selector);
                    log.Logback.getLogger().info("response was sent");
                    serverConnectionManager.stop();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            } catch (IOException ioe) {
                ioe.printStackTrace();
                try {
                    serverConnectionManager.stop();
                } catch (IOException ex) {
                    isRunning = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

    }

    @Override
    public void exit() throws IOException {
        //personCollection.save();
        log.Logback.getLogger().info("collection was saved");
        serverConnectionManager.stop();
        isRunning = false;
    }

    private void consoleStart() {
        Thread consoleThread = new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while (!Thread.interrupted()) {
                try {
                    input = reader.readLine();
                    List<String> commandParameters = new ArrayList<>();
                    commandParameters.addAll(Arrays.asList(input.trim().toLowerCase().split("\\s+")));
                    ServerCommand serverCommand = factory.chooseCommand(commandParameters.get(0));
                    if (serverCommand == null) {
                        System.out.println("Incorrect command input");
                        continue;
                    }
                    serverCommand.execute();
                } catch (IOException e) {

                }
            }
        });
        consoleThread.setDaemon(true);
        consoleThread.start();
    }
}
