package client;

import application.Application;
import exceptions.IncorrectFileSettings;

import java.net.InetAddress;

/**
 * Entry point in this program
 */
public class Main {
    public static void main(String[] args) {

        try {
            String address = "localhost";
            int port = 8080;
            Application client = new Client(address, port);

            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
