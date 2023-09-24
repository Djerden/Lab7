package commands;

import collection.PersonCollection;
import person.Person;
import user.Auth;

import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * A command that outputs all objects in the collection
 */
public class ShowCommand implements Command {
    private String result = "";
    private PersonCollection personCollection;
    private transient SocketChannel socketChannel;

    public ShowCommand() {
    }

    @Override
    public void setAuth(Auth auth) {

    }

    @Override
    public void execute() {

        List<Person> tempList = personCollection.show();
        if (tempList.isEmpty()) {
            result = "The collection is empty";
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
    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    @Override
    public String toString() {
        return "show";
    }
}
