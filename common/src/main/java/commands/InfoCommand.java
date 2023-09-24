package commands;

import collection.PersonCollection;
import user.Auth;

import java.nio.channels.SocketChannel;


/**
 * Command that outputs information about the collection
 */
public class InfoCommand implements Command {
    private String result = null;
    private PersonCollection personCollection;

    private transient SocketChannel socketChannel;

    public InfoCommand() {
    }

    @Override
    public void setAuth(Auth auth) {

    }

    @Override
    public void execute() {
        result = personCollection.info();
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
        return "info";
    }
}
