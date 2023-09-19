package command_reader;

import commands.Command;
import data.User;
import person.Country;
import person.Person;

import java.io.IOException;

/**
 * Data reading interface
 */
public interface CommandReader {
    Command readCommands() throws IOException;
    Person readPerson();
    Country readCountry();

    User readUser();
}
