package commands;

import collection.PersonCollection;
import exceptions.AbsenceArgumentException;
import user.Auth;

import java.nio.channels.SocketChannel;


/**
 * A command that allows you to delete an object from the collection by key
 */
public class RemoveByKeyCommand implements SimpleArgCommand {
    private String result = null;
    private PersonCollection personCollection;
    private String number;
    private Auth auth;

    private transient SocketChannel socketChannel;

    public RemoveByKeyCommand() {
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    @Override
    public void execute() {
        result = personCollection.remove_key(number, auth.getLogin());
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
        if (str != null) {
            number = str;
        } else {
            throw new AbsenceArgumentException("For this command, you must pass the passport argument");
        }
    }
    @Override
    public String toString() {
        return "remove_key";
    }
}
