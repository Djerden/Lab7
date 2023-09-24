package commands;

import collection.PersonCollection;
import user.Auth;

import java.nio.channels.SocketChannel;


/**
 * The command that outputs the object with the maximum weight
 */
public class MaxByWeightCommand implements Command{
    private String result = "";
    private PersonCollection personCollection;

    private transient SocketChannel socketChannel;

    public MaxByWeightCommand() {
    }

    @Override
    public void setAuth(Auth auth) {

    }

    @Override
    public void execute() {

        try {
        result = personCollection.max_by_weight().toString();
        } catch (NullPointerException e) {
            result = "The collection is empty";
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
        return "max_by_weight";
    }
}
