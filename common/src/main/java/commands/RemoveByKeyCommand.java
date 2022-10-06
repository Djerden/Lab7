package commands;

import collection.PersonCollection;
import exceptions.AbsenceArgumentException;


/**
 * A command that allows you to delete an object from the collection by key
 */
public class RemoveByKeyCommand implements SimpleArgCommand {
    private String result = null;
    private PersonCollection personCollection;
    private String number;

    public RemoveByKeyCommand() {
    }

    @Override
    public void execute() {
        personCollection.remove_key(number);
        result = "Человек с номером " + number + " удален";
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
            number = str;
        } else {
            throw new AbsenceArgumentException("Для этой команды необходимо передать аргумент - паспорт");
        }
    }
    @Override
    public String toString() {
        return "remove_key";
    }
}
