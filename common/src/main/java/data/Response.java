package data;

import java.io.Serializable;
import java.nio.channels.SocketChannel;

public class Response implements Serializable {
    private String message;
    private transient SocketChannel socketChannel;

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }
    public SocketChannel getSocketChannel() {
        return socketChannel;
    }
}
