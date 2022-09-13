import commands.Command;
import commands.HelpCommand;
import io.ConsoleWriter;

public class HelpCommandTest {
    public static void main(String[] args) {
        Command help = new HelpCommand(new ConsoleWriter());
        help.execute();
    }
}
