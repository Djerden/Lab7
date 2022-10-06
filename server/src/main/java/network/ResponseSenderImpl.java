package network;

import data.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ResponseSenderImpl implements ResponseSender{

    private Selector selector;
    @Override
    public void sendResponse(Response response, Selector selector) throws IOException {
        this.selector = selector;
        sendBytes(serializeResponse(response));

    }
    private byte[] serializeResponse(Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        return byteArrayOutputStream.toByteArray();
    }
    private void sendBytes(byte[] bytes) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        SocketChannel socketChannel = null;
        while (socketChannel == null) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isWritable()) {
                    socketChannel = (SocketChannel) key.channel();
                    socketChannel.write(byteBuffer);
                }
            }
        }
    }


}
