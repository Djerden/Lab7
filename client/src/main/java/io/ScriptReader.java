package io;

import application.CommandFactory;
import command_reader.CommandReader;
import commands.Command;
import commands.ObjectArgCommand;
import commands.SimpleArgCommand;
import data.User;
import exceptions.InvalidPersonFieldException;
import exceptions.UnknownCommandException;
import person.*;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class for reading user scripts
 */
public class ScriptReader implements CommandReader {
    private CommandFactory factory;
    private Scanner scanner;
    private io.Writer writer;

    public ScriptReader(Scanner scanner, CommandFactory factory, Writer writer) {
        this.scanner = scanner;
        this.factory = factory;
        this.writer = writer;
    }
    @Override
    public Command readCommands() throws IOException {
        String input;

        input = scanner.nextLine();
        input.trim();
        while (input == null || input.isEmpty()) {
            input = scanner.nextLine();
        }
        List<String> commandParameters = new ArrayList<>();
        commandParameters.addAll(Arrays.asList(input.trim().toLowerCase().split("\\s+")));
        if (commandParameters.size() < 2) {
            commandParameters.add(null);
        }
        factory.resetCommands();
        Command command = factory.chooseCommand(commandParameters.get(0));
        if (command == null) {
            throw new UnknownCommandException();
        }

        if (command instanceof SimpleArgCommand) {
            ((SimpleArgCommand) command).setSimpleArg(commandParameters.get(1));
        }

        if (command instanceof ObjectArgCommand) {
            ((ObjectArgCommand)command).setNeededObjects(this);
        }

        return command;
    }

    @Override
    public Person readPerson() {
        Person person = new DefaultPerson();
        readName(person);
        readCoordinates(person);
        readHeight(person);
        readWeight(person);
        readPassportId(person);
        readNationality(person);
        readLocation(person);
        setCreationDate(person);
        return person;
    }

    @Override
    public Country readCountry() {
        Country country = null;
        String[] countries = Country.returnCountries();

        try {
            String input = checkEmptyString().toLowerCase();
            country = Country.switchCountry(input);
            if (country == null) throw new InvalidPersonFieldException("This country is not in the list");

        } catch (InvalidPersonFieldException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect country entry");
        }
        return country;
    }

    @Override
    public User readUser() {
        return null;
    }

    private void setCreationDate(Person person) {
        ZoneId zoneId = ZoneId.of(Country.selectZoneId(person.getNationality()));
        person.setCreationDate(ZonedDateTime.now(zoneId));
    }

    private void readLocation(Person person) {
        try {
            Location location = new Location();
            Double x = Double.valueOf(checkEmptyString());
            location.setX(x);
            double y = Double.valueOf(checkEmptyString());
            location.setY(y);
            String name = scanner.nextLine().trim();
            location.setName(name);
            person.setLocation(location);
        } catch(InvalidPersonFieldException | NumberFormatException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect location input");
        }
    }

    private void readNationality(Person person) {
        try {
            Country p;
            String input = checkEmptyString().toLowerCase();
            Country country = Country.switchCountry(input);
            if (country == null) {
                throw new InvalidPersonFieldException("This country is not in the list");
            }
            person.setNationality(country);
        } catch (InvalidPersonFieldException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect entry of nationality ");
        }
    }

    private void readPassportId(Person person) {
        try {
            String passportId = checkEmptyString();
            person.setPassportID(passportId);
        } catch (InvalidPersonFieldException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect passport data entry ");
        }
    }

    private void readWeight(Person person) {
        try {
            Long weight = Long.valueOf(checkEmptyString());
            person.setWeight(weight);
        } catch (InvalidPersonFieldException | NumberFormatException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect weight input ");
        }
    }

    private void readHeight(Person person) {
        try {
            Double height = Double.valueOf(checkEmptyString());
            person.setHeight(height);
        } catch (InvalidPersonFieldException | NumberFormatException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect growth input ");
        }
    }

    private void readCoordinates(Person person) {
        try {
            Coordinates coordinates = new Coordinates();
            Integer x = Integer.valueOf(checkEmptyString());
            coordinates.setX(x);
            Integer y = Integer.valueOf(checkEmptyString());
            coordinates.setY(y);
            person.setCoordinates(coordinates);
        } catch(InvalidPersonFieldException | NumberFormatException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect input of coordinates ");
        }
    }

    private void readName(Person person) {
        try {
            String name = checkEmptyString().toLowerCase();
            person.setName(name);
        } catch (InvalidPersonFieldException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect name input ");
        }
    }

    private String checkEmptyString() {
        String input = scanner.nextLine();
        input.trim();
        while (input == null || input.isEmpty()) {
            input = scanner.nextLine().trim();
        }
        return input;
    }


}
