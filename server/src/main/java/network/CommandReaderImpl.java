package network;

import commands.Command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class CommandReaderImpl implements CommandReader{

    private Selector selector;
    @Override
    public Command readCommand(Selector selector) throws IOException, ClassNotFoundException {
        this.selector = selector;
        return deserializeCommand(readBytes());
    }

    byte[] readBytes() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        SocketChannel socketChannel = null;
        while (socketChannel == null) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    socketChannel = (SocketChannel) key.channel();
                    socketChannel.read(byteBuffer);
                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                }
                iterator.remove();
            }
        }
        return byteBuffer.array();
    }

    private Command deserializeCommand(byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (Command) objectInputStream.readObject();
    }
}
