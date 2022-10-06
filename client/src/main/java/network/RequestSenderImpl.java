package network;

import commands.Command;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.channels.SocketChannel;

public class RequestSenderImpl implements RequestSender {

    private OutputStream outputStream;

    @Override
    public void initOutputStream(SocketChannel socketChannel) throws IOException {
        outputStream = socketChannel.socket().getOutputStream();
    }

    @Override
    public void sendRequest(Command command, SocketChannel socketChannel) throws IOException {
        ByteArrayOutputStream toByte = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(toByte);
        objectOutputStream.writeObject(command);
        outputStream.write(toByte.toByteArray());
        outputStream.flush();
    }
}
