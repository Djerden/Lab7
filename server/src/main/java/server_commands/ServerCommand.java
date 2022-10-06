package server_commands;

import java.io.IOException;

public interface ServerCommand {
    void execute() throws IOException;
}
