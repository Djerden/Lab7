package network;

import commands.Command;
import data.Response;

public interface RequestHandler {
    Response handleRequest(Command command);
}
