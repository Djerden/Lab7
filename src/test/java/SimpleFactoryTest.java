import client.Client;
import client.CommandFactory;
import client.CommandSimpleFactory;
import collection.HashMapPersonCollection;
import collection.JsonPersonReader;
import collection.JsonPersonWriter;
import commands.Command;
import commands.SimpleArgCommand;
import exceptions.AbsenceArgumentException;
import io.ConsoleWriter;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        String name = "C:\\Users\\Number_One\\Downloads\\Json.txt";
        CommandFactory factory = new CommandSimpleFactory(new HashMapPersonCollection(new JsonPersonReader(name), new JsonPersonWriter(name)), new Client(name), new ConsoleWriter());

         SimpleArgCommand command = (SimpleArgCommand) factory.chooseCommand("remove_key");
         try {
             command.setSimpleArg(null);
             command.execute();
         } catch (AbsenceArgumentException e) {
             System.out.println("Неверно введен аргумент ");
         }
         System.out.println(command.toString());


    }
}
