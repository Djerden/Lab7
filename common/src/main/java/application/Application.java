package application;

import commands.Command;

import java.io.IOException;
import java.util.Queue;

/**
 * Facade encapsulating the logic of the program
 */
public interface Application {
    void start();
    void exit() throws IOException;
}
