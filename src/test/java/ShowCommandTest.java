import collection.HashMapPersonCollection;
import collection.JsonPersonReader;
import collection.JsonPersonWriter;
import collection.PersonCollection;
import commands.Command;
import commands.ShowCommand;
import io.ConsoleWriter;
import person.*;

public class ShowCommandTest {
    public static void main(String[] args) {
        PersonCollection collection = new HashMapPersonCollection(new JsonPersonReader(""), new JsonPersonWriter(""));

        Person person5 = new DefaultPerson("Alexandr", new Coordinates(12, 12), 186.0, 70L,
                "4017677987", Country.CHINA, new Location(56.0, 78, "HO SHI MIN"));

        Person person2 = new DefaultPerson("Dmitrii", new Coordinates(24, 54), 180.0, 85L,
                "4014321987", Country.NORTH_KOREA, new Location(34.0, 14, "STO TO TAM"));

        Person person3 = new DefaultPerson("Teymur", new Coordinates(17, 10), 173.0, 86L,
                "3917677129", Country.SOUTH_KOREA, new Location(88.0, 58, "GTE TO"));

        Person person4 = new DefaultPerson("Derdik", new Coordinates(17, 10), 183.0, 82L,
                "3917677129", Country.SOUTH_KOREA, new Location(88.0, 58, "Kiroto"));


        Person person1 = new DefaultPerson("Alexandr", new Coordinates(12, 12), 186.0, 70L,
                "4017677987", Country.CHINA, new Location(56.0, 78, "HO SHI MIN"));


        collection.insert("1", person5);
        collection.insert("2", person1);
        collection.insert("3", person2);
        collection.insert("4", person3);
        collection.insert("5", person4);

        Command show = new ShowCommand(collection, new ConsoleWriter());
        show.execute();
    }
}