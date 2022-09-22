package commands;

import collection.PersonCollection;
import exceptions.AbsenceArgumentException;
import io.CommandReader;
import io.Writer;
import person.Person;

/**
 * A command that replaces another object by id
 */
public class UpdateByIdCommand implements ObjectArgCommand {
    private PersonCollection personCollection;
    private Writer writer;
    private Integer id;
    private Person person;

    public UpdateByIdCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        personCollection.update(id, person);
        writer.write("Человек с id=" + id + " успешно заменен");

    }

    @Override
    public void setNeededObjects(CommandReader reader) {
        person = reader.readPerson();
    }

    @Override
    public void setSimpleArg(String str) {
        if (str != null) {
            id = Integer.valueOf(str);
        } else {
            throw new AbsenceArgumentException("Для этой команды необходимо передать аргумент - id");
        }
    }

    @Override
    public String toString() {
        return "update";
    }
}
