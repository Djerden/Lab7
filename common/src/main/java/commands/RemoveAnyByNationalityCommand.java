package commands;

import collection.PersonCollection;
import command_reader.CommandReader;
import person.Country;
import person.Person;
import user.Auth;

import java.nio.channels.SocketChannel;

/**
 * A command that allows you to delete an object from a collection with a given country
 */
public class RemoveAnyByNationalityCommand implements ObjectArgCommand {
    private String result = "";
    private PersonCollection personCollection;
    private Country country;
    private Auth auth;
    private transient SocketChannel socketChannel;

    public RemoveAnyByNationalityCommand() {
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    @Override
    public void execute() {

        Person person = personCollection.remove_any_by_nationality(country, auth.getLogin());
        if (person == null) {
            result = "There are no items with the selected country in the collection";
        } else {
            result = "Object deleted: name = " + person.getName() + ", id = " + person.getId();
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
    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public SocketChannel getSocketChannel() {
        return socketChannel;
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
