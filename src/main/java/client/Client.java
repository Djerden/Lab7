package client;

public class Client implements Application {

    private boolean isRunning;

    @Override
    public void start() {
        isRunning = true;
    }

    @Override
    public void exit() {
        isRunning = false;
    }
}
