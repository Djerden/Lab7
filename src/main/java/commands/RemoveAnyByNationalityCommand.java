package commands;

import collection.PersonCollection;
import io.CommandReader;
import person.Country;

public class RemoveAnyByNationalityCommand implements ObjectArgCommand {

    private PersonCollection personCollection;
    private Country country;

    public RemoveAnyByNationalityCommand(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute() {
        personCollection.remove_any_by_nationality(country);
    }

    @Override
    public void setSimpleArg(String str) {

    }

    @Override
    public void setNeededObjects(CommandReader reader) {
        country = reader.readCountry();
    }
}
