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
    private SelectionKey selectionKey;
    @Override
    public Command readCommand(SelectionKey selectionKey) throws IOException, ClassNotFoundException {
        this.selectionKey = selectionKey;
        return deserializeCommand(readBytes());
    }

    private Command deserializeCommand(byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));

        Command command = (Command) objectInputStream.readObject();
        command.setSocketChannel((SocketChannel) selectionKey.channel());
        return command;
    }

    byte[] readBytes() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            socketChannel.read(byteBuffer);
        } else {
            System.out.println("Данные не прочитаны в классе CommandReader");
        }
        return byteBuffer.array();
    }
}
