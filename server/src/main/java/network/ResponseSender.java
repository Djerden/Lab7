package network;

import data.Response;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public interface ResponseSender {
    void sendResponse(SocketChannel socketChannel, Response response) throws IOException;
}
