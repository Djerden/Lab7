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
    public void sendResponse(SocketChannel socketChannel, Response response) throws IOException {
        sendBytes(serializeResponse(response), socketChannel);

    }
    private byte[] serializeResponse(Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        return byteArrayOutputStream.toByteArray();
    }
    private void sendBytes(byte[] bytes, SocketChannel socketChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        int numWritten = 1;
        try {
            while (byteBuffer.remaining() > 0) {
                numWritten = socketChannel.write(byteBuffer);
            }
        } catch (IOException e) {
            socketChannel.close();
            e.printStackTrace();
        }
    }
}
