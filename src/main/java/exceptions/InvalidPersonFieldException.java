package exceptions;

public class InvalidPersonFieldException extends RuntimeException {

    public InvalidPersonFieldException(String message) {
        super(message);
    }
}
