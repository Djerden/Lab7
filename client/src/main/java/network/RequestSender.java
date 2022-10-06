package network;

import commands.Command;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface RequestSender {
    void initOutputStream(SocketChannel socketChannel) throws IOException;
    void sendRequest(Command command, SocketChannel socketChannel) throws IOException   ;
}
