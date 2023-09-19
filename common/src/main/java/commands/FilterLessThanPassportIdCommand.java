package commands;

import collection.PersonCollection;
import exceptions.AbsenceArgumentException;
import person.Person;
import user.Auth;

import java.util.List;
/**
 * Command that outputs objects of the Person passport Id type that are less than the specified one
 */
public class FilterLessThanPassportIdCommand implements SimpleArgCommand{
    private String result = "";
    private PersonCollection personCollection;
    private String passportId;


    public FilterLessThanPassportIdCommand() {
    }

    @Override
    public void setAuth(Auth auth) {

    }

    @Override
    public void execute() {

        List<Person> tempList = personCollection.filter_less_than_passport_id(passportId);
        if (tempList.isEmpty()) {
            result = "There are no such elements";
        } else {
            for (Person i : tempList) {
                result = result + i.toString() + "\n";
            }
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
        if (str != null) {
            passportId = str;
        } else {
            throw new AbsenceArgumentException("For this command, you must pass the passport argument");
        }

    }

    @Override
    public String toString() {
        return "filter_less_than_passport_id";
    }
}
