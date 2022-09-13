package commands;

import collection.PersonCollection;
import io.Writer;
import person.Person;

import java.util.List;

public class FilterLessThanPassportIdCommand implements SimpleArgCommand{

    private PersonCollection personCollection;
    private Writer writer;
    private String passportId;

    public FilterLessThanPassportIdCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        List<Person> tempList = personCollection.filter_less_than_passport_id(passportId);
        for (Person i : tempList) {
            writer.write(i.toString());
        }
    }

    @Override
    public void setSimpleArg(String str) {
        passportId = str;
    }
}
