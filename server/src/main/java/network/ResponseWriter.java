package network;

import data.Response;

public interface ResponseWriter {
    Response writeResponse(String message);
}
