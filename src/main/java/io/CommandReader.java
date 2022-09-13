package io;

import commands.Command;
import person.Country;
import person.Person;

import java.io.IOException;

public interface CommandReader {
    Command readCommands() throws IOException;
    Person readPerson();
    Country readCountry();
}
