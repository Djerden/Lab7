package exceptions;

public class UnknownCommandException extends RuntimeException {

    public UnknownCommandException() {
        super("Unknown command");
    }

    public UnknownCommandException(String message) {
        super(message);
    }

}
