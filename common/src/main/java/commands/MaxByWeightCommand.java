package commands;

import collection.PersonCollection;


/**
 * The command that outputs the object with the maximum weight
 */
public class MaxByWeightCommand implements Command{
    private String result = "";
    private PersonCollection personCollection;

    public MaxByWeightCommand() {
    }

    @Override
    public void execute() {

        try {
        result = personCollection.max_by_weight().toString();
        } catch (NullPointerException e) {
            result = "Коллекция пуста";
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
    public String toString() {
        return "max_by_weight";
    }
}
