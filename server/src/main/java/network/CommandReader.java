package network;

import commands.Command;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public interface CommandReader {
    Command readCommand(SelectionKey selectionKey) throws IOException, ClassNotFoundException;
}
