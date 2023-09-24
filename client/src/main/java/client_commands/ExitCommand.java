package client_commands;

import application.Application;
import collection.PersonCollection;
import commands.Command;
import user.Auth;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * Exit command from the program
 */
public class ExitCommand implements Command {
    private Application application;

    public ExitCommand(Application application) {
        this.application = application;
    }

    @Override
    public void setAuth(Auth auth) {

    }

    @Override
    public void execute() {

        try {
            application.exit();
        } catch (IOException e) {
            System.out.println("something went wrong with the exit");
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
    public String toString() {
        return "exit";
    }
}
