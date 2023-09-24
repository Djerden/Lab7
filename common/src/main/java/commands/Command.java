package commands;

import collection.PersonCollection;
import user.Auth;

import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Command Interface
 */
public interface Command extends Serializable {

    void setAuth(Auth auth);
    void execute();

    void setCollection(PersonCollection personCollection);

    String getResult();

    void setSocketChannel(SocketChannel socketChannel);
    SocketChannel getSocketChannel();
}
