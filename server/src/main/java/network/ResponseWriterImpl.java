package network;

import data.Response;

public class ResponseWriterImpl implements ResponseWriter{
    @Override
    public Response writeResponse(String message) {
        return new Response(message);
    }
}
