import client.Client;
import client.CommandSimpleFactory;
import collection.HashMapPersonCollection;
import collection.JsonPersonReader;
import collection.JsonPersonWriter;
import commands.Command;
import commands.HelpCommand;
import commands.ObjectArgCommand;
import commands.SimpleArgCommand;
import exceptions.UnknownCommandException;
import io.CommandReader;
import io.ConsoleCommandReader;
import io.ConsoleWriter;
import person.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ReadAndFactoryTest {
    public static void main(String[] args) throws IOException {
        CommandReader reader = new ConsoleCommandReader(new CommandSimpleFactory(new HashMapPersonCollection(new JsonPersonReader(""), new JsonPersonWriter("")), new Client(), new ConsoleWriter()), new ConsoleWriter());
        Command com = new HelpCommand(new ConsoleWriter());
        if (com instanceof Command) System.out.println("YES");
        if (com instanceof SimpleArgCommand) System.out.println("YES");
        if (com instanceof ObjectArgCommand) System.out.println("YES");

        System.out.println("Вводите команды: ");
        while (true) {
            try {
                reader.readCommands().execute();
            } catch (UnknownCommandException e) {
                System.out.println("Команда не распознана, попробуйте еще раз");
            }
        }
    }
}
    class TestObjectArgCommand implements ObjectArgCommand {

        @Override
        public void execute() {

        }

        @Override
        public void setNeededObjects(CommandReader reader) {

        }

        @Override
        public void setSimpleArg(String str) {

        }
    }


