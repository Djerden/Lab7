import collection.PersonCollection;
import commands.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializationTest {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\Number_One\\Downloads\\ser.txt");
        Command command = new RemoveGreaterByKeyCommand();
        ((SimpleArgCommand) command).setSimpleArg("gggg");
        try {
            FileOutputStream outputStream = new FileOutputStream(String.valueOf(path));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(command);
            System.out.println("Данные сериализованы");
            objectOutputStream.close();


            FileInputStream fileInputStream = new FileInputStream(String.valueOf(path));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Command command1 = (Command) objectInputStream.readObject();
            System.out.println(command.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        }
    }
}
