import client.Application;
import client.Client;
import collection.HashMapPersonCollection;
import commands.*;
import io.ConsoleWriter;

import java.util.LinkedList;
import java.util.Queue;

public class HistoryTest {
    public static void main(String[] args) {
        Command history = new HistoryCommand(new App(), new ConsoleWriter());
        history.execute();
    }
}
class App implements Application {

    @Override
    public void start() {

    }

    @Override
    public void exit() {

    }

    @Override
    public Queue<Command> getHistory() {
        Queue<Command> commands = new LinkedList<>();
        commands.offer(new HelpCommand(new ConsoleWriter()));
        commands.offer(new ExitCommand(this));
        return commands;
    }
}
