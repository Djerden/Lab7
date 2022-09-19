import client.Client;
import client.CommandFactory;
import client.CommandSimpleFactory;
import collection.HashMapPersonCollection;
import collection.JsonPersonReader;
import collection.JsonPersonWriter;
import collection.PersonCollection;
import commands.Command;
import io.CommandReader;
import io.ConsoleCommandReader;
import io.ConsoleWriter;
import io.Writer;

import java.io.IOException;

public class ReadTest {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\Number_One\\Downloads\\Json.txt";

        Writer writer = new ConsoleWriter();
        PersonCollection personCollection = new HashMapPersonCollection(new JsonPersonReader(fileName), new JsonPersonWriter(fileName));
        CommandFactory factory = new CommandSimpleFactory(personCollection, new Client(fileName), writer);

        CommandReader reader = new ConsoleCommandReader(factory, writer);

        while (true) {
            try {
                Command command = reader.readCommands();
            } catch (IOException e) {
            }
        }
    }
}
