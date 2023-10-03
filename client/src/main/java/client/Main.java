package client;

import application.Application;
import exceptions.IncorrectFileSettings;

import java.net.InetAddress;

/**
 * Entry point in this program
 */
public class Main {
    public static void main(String[] args) {

            String address = "localhost";
            int port = 3232;
            Application client = new Client(address, port);

        try {
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
