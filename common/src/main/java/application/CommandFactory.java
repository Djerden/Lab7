package application;

import commands.Command;

/**
 * Factory interface that allows you to select the necessary command
 */
public interface CommandFactory {
    Command chooseCommand(String nameCommand);
}
