package commands;

/**
 * Interface for commands with a simple argument
 */
public interface SimpleArgCommand extends Command {
    void setSimpleArg(String str);
}
