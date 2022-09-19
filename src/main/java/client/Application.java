package client;

import commands.Command;

import java.util.Queue;

public interface Application {
    void start();
    void exit();

    Queue<Command> getHistory();
}
