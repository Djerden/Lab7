package commands;

import client.Application;
import io.Writer;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class HistoryCommand implements Command{

    private Application application;
    private Writer writer;
    private Queue<Command> commands;

    public HistoryCommand(Application application, Writer writer) {
        this.application = application;
        this.writer = writer;
        commands = application.getHistory();
    }
    @Override
    public void execute() {
        ListIterator<Command> listIterator = ((LinkedList<Command>)commands).listIterator(commands.size());
        while (listIterator.hasPrevious()) {
            writer.write(listIterator.previous().toString());
        }
    }
}
