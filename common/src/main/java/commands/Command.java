package commands;

import collection.PersonCollection;
import user.Auth;

import java.io.Serializable;

/**
 * Command Interface
 */
public interface Command extends Serializable {

    void setAuth(Auth auth);
    void execute();

    void setCollection(PersonCollection personCollection);

    String getResult();
}
