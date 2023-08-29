package network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerConnectionManagerImpl implements ServerConnectionManager{
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    @Override
    public void openConnection(String address, int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(address, port));
        serverSocketChannel.configureBlocking(false);
        int ops = serverSocketChannel.validOps();
        serverSocketChannel.register(selector, ops);
    }

    <T> T factory(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }
    @Override
    public Selector listen() throws IOException {
        selector.select();
        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> selectionKeyIterator = keys.iterator();
        while (selectionKeyIterator.hasNext()) {
            SelectionKey key = selectionKeyIterator.next();
            if (key.isAcceptable()) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
            selectionKeyIterator.remove();
        }

        return selector;
    }

    @Override
    public void stop() throws IOException {
        selector.close();
        serverSocketChannel.socket().close();
        serverSocketChannel.close();
    }
    public Selector getSelector() {
        return selector;
    }
}
