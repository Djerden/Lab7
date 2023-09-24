package client_commands;

import application.Application;
import client.HistoryFunction;
import collection.PersonCollection;
import commands.Command;
import io.Writer;
import user.Auth;

import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

/**
 *  Command that outputs the last 9 commands used
 */
public class HistoryCommand implements Command {

    private HistoryFunction application;
    private Writer writer;
    private Queue<Command> commands;

    public HistoryCommand(HistoryFunction application, Writer writer) {
        this.application = application;
        this.writer = writer;
    }

    @Override
    public void setAuth(Auth auth) {

    }

    @Override
    public void execute() {
        commands = application.getHistory();
        ListIterator<Command> listIterator = ((LinkedList<Command>)commands).listIterator(commands.size());
        while (listIterator.hasPrevious()) {
            writer.write(listIterator.previous().toString());
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
        return "history";
    }
}
