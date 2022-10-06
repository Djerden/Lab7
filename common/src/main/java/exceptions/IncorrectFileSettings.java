package exceptions;

public class IncorrectFileSettings extends RuntimeException {
    public IncorrectFileSettings() {
        super("Неверно задана переменная окружения");
    }
}
