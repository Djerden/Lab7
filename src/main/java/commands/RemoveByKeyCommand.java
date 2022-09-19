package commands;

import collection.PersonCollection;
import exceptions.AbsenceArgumentException;
import io.Writer;

public class RemoveByKeyCommand implements SimpleArgCommand {

    private PersonCollection personCollection;
    private Writer writer;
    private String number;

    public RemoveByKeyCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        personCollection.remove_key(number);
        writer.write("Объект успешно удален");
    }

    @Override
    public void setSimpleArg(String str) {
        if (str != null) {
            number = str;
        } else {
            throw new AbsenceArgumentException("Для этой команды необходимо передать аргумент - паспорт");
        }
    }
}
