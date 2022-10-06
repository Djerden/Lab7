package network;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface ConnectionManager {

    public SocketChannel openConnection(String addres, int port) throws IOException;
}
