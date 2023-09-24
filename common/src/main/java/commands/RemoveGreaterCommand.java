package commands;

import collection.PersonCollection;
import command_reader.CommandReader;
import person.Person;
import user.Auth;

import java.nio.channels.SocketChannel;

/**
 * A command that allows you to delete objects whose values have more parameters than the specified object
 */
public class RemoveGreaterCommand implements ObjectArgCommand {
    private String result = null;
    private PersonCollection personCollection;
    private Person person;
    private Auth auth;
    private transient SocketChannel socketChannel;

    public RemoveGreaterCommand() {
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    @Override
    public void execute() {
        result = personCollection.remove_greater(person, auth.getLogin());
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
    public void setNeededObjects(CommandReader reader) {
        person = reader.readPerson();
    }

    @Override
    public void setSimpleArg(String str) {

    }
    @Override
    public String toString() {
        return "remove_greater";
    }
}
