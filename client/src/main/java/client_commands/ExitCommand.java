package client_commands;

import application.Application;
import collection.PersonCollection;
import commands.Command;

import java.io.IOException;

/**
 * Exit command from the program
 */
public class ExitCommand implements Command {
    private Application application;

    public ExitCommand(Application application) {
        this.application = application;
    }

    @Override
    public void execute() {

        try {
            application.exit();
        } catch (IOException e) {
            System.out.println("что-то пошло не так с выходом");
        }
    }

    @Override
    public void setCollection(PersonCollection personCollection) {

    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public String toString() {
        return "exit";
    }
}
