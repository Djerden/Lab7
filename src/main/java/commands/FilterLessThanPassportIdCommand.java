package commands;

import collection.PersonCollection;
import exceptions.AbsenceArgumentException;
import io.Writer;
import person.Person;

import java.util.List;

public class FilterLessThanPassportIdCommand implements SimpleArgCommand{

    private PersonCollection personCollection;
    private Writer writer;
    private String passportId;

    /**
     * Command that outputs objects of the Person passport Id type that are less than the specified one
     */
    public FilterLessThanPassportIdCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        List<Person> tempList = personCollection.filter_less_than_passport_id(passportId);
        if (tempList.isEmpty()) {
            writer.write("Таких элементов нет");
        } else {
            for (Person i : tempList) {
                writer.write(i.toString());
            }
        }
    }

    @Override
    public void setSimpleArg(String str) {
        if (str != null) {
            passportId = str;
        } else {
            throw new AbsenceArgumentException("Для этой команды необходимо передать аргумент - паспорт");
        }

    }
}
