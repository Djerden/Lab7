package client;

import commands.Command;

import java.util.Queue;

/**
 * Facade encapsulating the logic of the program
 */
public interface Application {
    void start();
    void exit();

    Queue<Command> getHistory();
}
