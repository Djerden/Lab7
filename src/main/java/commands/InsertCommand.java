package commands;

import collection.PersonCollection;
import exceptions.AbsenceArgumentException;
import io.CommandReader;
import io.Writer;
import person.Person;

public class InsertCommand implements ObjectArgCommand {

    private PersonCollection personCollection;

    private Writer writer;
    private String number;
    private Person person;

    /**
     * Command to add objects of the Person type to the collection
     */
    public InsertCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        personCollection.insert(number, person);
        writer.write("Элемент с ключом " + number + " успешно добавлен");
    }

    @Override
    public void setNeededObjects(CommandReader reader) {
        person = reader.readPerson();
    }

    @Override
    public void setSimpleArg(String str) {
        if (str != null) {
            number = str;
        } else {
            throw new AbsenceArgumentException("Для этой команды необходимо передать аргумент - номер");
        }
    }
}
