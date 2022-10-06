package network;

import commands.Command;

import java.io.IOException;
import java.nio.channels.Selector;

public interface CommandReader {
    Command readCommand(Selector selector) throws IOException, ClassNotFoundException;
}
