package exceptions;

public class UnknownCommandException extends RuntimeException {

    public UnknownCommandException() {
        super("Неизвестная команда");
    }

    public UnknownCommandException(String message) {
        super(message);
    }

}
