package commands;

import client.Application;

public class ExitCommand implements Command {
    private Application application;

    public ExitCommand(Application application) {
        this.application = application;
    }

    @Override
    public void execute() {
        application.exit();
    }
}
