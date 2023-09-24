package network;

import commands.Command;
import data.RequestOpsState;
import data.Response;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.*;

import static java.nio.channels.SelectionKey.OP_WRITE;

public class ServerExec implements Runnable {


    private ServerConnectionManagerImpl2 connectionListener;
    private CommandReader commandReader;
    private RequestHandler requestHandler;
    private ResponseSender responseSender;

    private Selector selector;
    private final Map<SocketChannel, Response> responseMap = new ConcurrentHashMap<>();
    private final List<RequestOpsState> changeRequests = new CopyOnWriteArrayList<>();

    private ExecutorService readRequests = Executors.newCachedThreadPool();
    private ExecutorService handleRequests = Executors.newCachedThreadPool();
    private ExecutorService sendResponses = Executors.newCachedThreadPool();


    public ServerExec(ServerConnectionManagerImpl2 listener, CommandReader reader, RequestHandler handler, ResponseSender sender) throws IOException {
        connectionListener = listener;
        commandReader = reader;
        requestHandler = handler;
        responseSender = sender;
        selector = connectionListener.openConnection();
    }

    @Override
    public void run() {
        while(true) {
            try {
                synchronized (changeRequests) {
                    for (RequestOpsState requestOpsState : changeRequests) {
                        if (requestOpsState.getType() == RequestOpsState.CHANGEOPS) {
                            try {
                                SelectionKey key = requestOpsState.getSocketChannel().keyFor(selector);
                                key.interestOps(requestOpsState.getOps());
                            } catch (Exception e){
                                log.Logback.getLogger().error("error with selection keys");
                                log.Logback.getLogger().error(e.getMessage());
                                e.printStackTrace();
                            }
                        } else if (requestOpsState.getType() == RequestOpsState.DEREGISTER) {
                            SelectionKey key = requestOpsState.getSocketChannel().keyFor(selector);
                            key.cancel();
                            requestOpsState.getSocketChannel().close();
                        }
                    }
                    changeRequests.clear();
                }

                selector.select();
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while(selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();

                    if (!key.isValid()) {
                        continue;
                    }

                    if (key.isAcceptable()) {
                        connectionListener.accept(key);
                    } else if (key.isReadable()) {
                        read(key);
                    } else if (key.isWritable()) {
                        sendResponse(key);
                        key.interestOps(0);
                    }
                }

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey selectionKey) throws IOException, ClassNotFoundException {
        Command command = commandReader.readCommand(selectionKey);
        Runnable readingRequestRunnable = () -> {
            handleRequest(command);
        };
        readRequests.submit(readingRequestRunnable);
    }

    private void sendResponse(SelectionKey selectionKey) {
        Runnable sendingResponseRunnable = () -> {
            try {
                synchronized (responseMap) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    responseSender.sendResponse(channel, responseMap.get(selectionKey.channel()));
                }
            } catch(IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка отправки ответа клиенту");
            }
        };
        sendResponses.submit(sendingResponseRunnable);
    }

    private void handleRequest(Command command) {
        Runnable handleRequestRunnable = () -> {
            Response response = requestHandler.handleRequest(command);
            response.setSocketChannel(command.getSocketChannel());
            prepareToSend(response);
            this.selector.wakeup();
        };
        handleRequests.submit(handleRequestRunnable);
    }
    private void prepareToSend(Response response) {
        synchronized (changeRequests) {
            changeRequests.add(new RequestOpsState(response.getSocketChannel(), RequestOpsState.CHANGEOPS, OP_WRITE));
            synchronized (responseMap) {
                responseMap.put(response.getSocketChannel(), response);
            }
        }
        this.selector.wakeup();
    }


}


