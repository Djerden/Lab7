package network;

import collection.PersonCollection;
import commands.Command;
import data.Response;

public class RequestHandlerImpl implements RequestHandler {
    private ResponseWriter writer;
    private PersonCollection personCollection;
    public RequestHandlerImpl(ResponseWriter writer, PersonCollection personCollection) {
        this.writer = writer;
        this.personCollection = personCollection;
    }
    @Override
    public Response handleRequest(Command command) {

        command.setCollection(personCollection);
        command.execute();

        return writer.writeResponse(command.getResult());
    }
}
