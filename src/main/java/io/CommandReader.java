package io;

import commands.Command;

import java.io.IOException;

public interface CommandReader {
    Command readCommands() throws IOException;
}
