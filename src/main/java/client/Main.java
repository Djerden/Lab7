package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\Number_One\\Downloads\\Json.txt";
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        /*
        try {
            fileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
        Application client = new Client(fileName);
        try {
            client.start();
        } catch (Exception e) {
            System.out.println("ERROR ERROR");
            e.printStackTrace();
        }
    }
}
