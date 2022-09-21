package client;

import exceptions.IncorrectFileSettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        //"C:\\Users\\Number_One\\Downloads\\Json.txt";
        try {
            String fileName = System.getenv("DATA_TO_LAB5");
            if (fileName == null) {
                throw new IncorrectFileSettings();
            }

            Application client = new Client(fileName);

            client.start();
        } catch (Exception e) {
            System.out.println("ERROR ERROR");
            e.printStackTrace();
        }
    }
}
