package client;

import commands.Command;

public interface CommandFactory {
    Command chooseCommand(String nameCommand);
}
