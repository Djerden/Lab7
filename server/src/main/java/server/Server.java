package server;

import application.Application;
import collection.HashMapPersonCollection;
import collection.PersonCollection;
import commands.Command;
import data.DBManager;
import data.DataManager;
import data.Response;
import network.*;
import server_commands.ServerCommand;
import server_commands.ServerCommandSimpleFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.Selector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server implements Application {

    //private static String dataFileName = System.getenv("DATA_TO_LAB5");

    private final CommandReader commandReader = new CommandReaderImpl();
    private final ResponseWriter responseWriter = new ResponseWriterImpl();
    private final ResponseSender responseSender = new ResponseSenderImpl();
    private final ServerConnectionManager serverConnectionManager = new ServerConnectionManagerImpl();

    private PersonCollection personCollection;

    private ServerCommandSimpleFactory factory = new ServerCommandSimpleFactory(this, personCollection);

    String address = "localhost";
    int port = 8080;

    private boolean isRunning;


    @Override
    public void start() {

        consoleStart();

        log.Logback.getLogger().info("server was started");
        isRunning = true;


        try {
            getDataFromDB();
            log.Logback.getLogger().info("data was parsed");
        } catch (Exception e) {}

        communicateWithClient();

        /*Selector selector;

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
                //new Thread(doCommand).start();
                 try {
                    String result;
                    Command command = commandReader.readCommand(selector);
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
        }*/

    }

    private void communicateWithClient() {
        ServerConnectionManagerImpl2 connectionListener = new ServerConnectionManagerImpl2();
        connectionListener.setSocketAddress(new InetSocketAddress(address, port));
        RequestHandler requestHandler = new RequestHandlerImpl(responseWriter, personCollection);
        log.Logback.getLogger().info("connection is open");
        System.out.println("Connection is open");
        try {
            ServerExec serverExec = new ServerExec(connectionListener, commandReader, requestHandler, responseSender);
            new Thread(serverExec).start();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getDataFromDB() throws FileNotFoundException, ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql:lab7";
        String user = "postgres";
        String pass = "1967228";
        DataManager dataManager = new DBManager(url, user, pass);
        personCollection = new HashMapPersonCollection(dataManager);

    }

    @Override
    public void exit() throws IOException {
        //personCollection.save();
        log.Logback.getLogger().info("collection was saved");
        isRunning = false;
        System.exit(0);
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
