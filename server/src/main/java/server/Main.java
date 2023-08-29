package server;

import application.Application;

public class Main {
    public static void main(String[] args) {
        Application server = new Server();

        try {
            server.start();
        } catch(Exception e) {
            e.getMessage();
        }
    }
}
