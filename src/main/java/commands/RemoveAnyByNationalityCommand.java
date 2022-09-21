package commands;

import collection.PersonCollection;
import io.CommandReader;
import io.Writer;
import person.Country;
import person.Person;

public class RemoveAnyByNationalityCommand implements ObjectArgCommand {

    private PersonCollection personCollection;
    private Writer writer;
    private Country country;

    public RemoveAnyByNationalityCommand(PersonCollection personCollection, Writer writer) {
        this.personCollection = personCollection;
        this.writer = writer;
    }

    @Override
    public void execute() {
        Person person = personCollection.remove_any_by_nationality(country);
        if (person == null) {
            writer.write("Элементов с выбранной страной нет в коллекции");
        } else {
            writer.write("Удален объект: name = " + person.getName() + ", id = " + person.getId());
        }
    }

    @Override
    public void setSimpleArg(String str) {

    }

    @Override
    public void setNeededObjects(CommandReader reader) {
        country = reader.readCountry();
    }
}
