package server_commands;

import application.Application;

import java.io.IOException;

public class ExitCommand implements ServerCommand{

    private Application application;

    public ExitCommand(Application application) {
        this.application = application;
    }

    @Override
    public void execute() throws IOException {

        application.exit();
        log.Logback.getLogger().info("exit from program");

    }
}
