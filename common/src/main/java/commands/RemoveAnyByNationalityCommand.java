package commands;

import collection.PersonCollection;
import command_reader.CommandReader;
import person.Country;
import person.Person;

/**
 * A command that allows you to delete an object from a collection with a given country
 */
public class RemoveAnyByNationalityCommand implements ObjectArgCommand {
    private String result = "";
    private PersonCollection personCollection;
    private Country country;

    public RemoveAnyByNationalityCommand() {
    }

    @Override
    public void execute() {

        Person person = personCollection.remove_any_by_nationality(country);
        if (person == null) {
            result = "Элементов с выбранной страной нет в коллекции";
        } else {
            result = "Удален объект: name = " + person.getName() + ", id = " + person.getId();
        }
    }

    @Override
    public void setCollection(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public void setSimpleArg(String str) {

    }

    @Override
    public void setNeededObjects(CommandReader reader) {
        country = reader.readCountry();
    }

    @Override
    public String toString() {
        return "remove_any_by_nationality";
    }
}
