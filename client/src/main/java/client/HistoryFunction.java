package client;

import commands.Command;

import java.util.Queue;

public interface HistoryFunction {
    Queue<Command> getHistory();
}
