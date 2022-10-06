package network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ConnectionManagerImpl implements ConnectionManager{

    private SocketChannel socketChannel;

    @Override
    public SocketChannel openConnection(String addres, int port) throws IOException {
        socketChannel = SocketChannel.open(new InetSocketAddress(addres, port));
        return socketChannel;
    }
}
