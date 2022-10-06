package commands;

import collection.PersonCollection;

import java.io.Serializable;

/**
 * Command Interface
 */
public interface Command extends Serializable {
    void execute();

    void setCollection(PersonCollection personCollection);

    String getResult();
}
