package network;

import data.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.channels.SocketChannel;

public class ResponseReaderImpl implements ResponseReader{
    @Override
    public Response getResponse(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[16384];
        InputStream inputStream = socketChannel.socket().getInputStream();
        inputStream.read(bytes);
        return deserializeResponse(bytes);
    }

    private Response deserializeResponse(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (Response) objectInputStream.readObject();
    }

}
