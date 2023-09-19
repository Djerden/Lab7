package io;

import application.CommandFactory;
import command_reader.CommandReader;
import commands.Command;
import commands.ObjectArgCommand;
import commands.SimpleArgCommand;
import commands.UserCommand;
import data.User;
import exceptions.InvalidPersonFieldException;
import exceptions.UnknownCommandException;
import person.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Console implementation of the CommandReader interface
 */
public class ConsoleCommandReader implements CommandReader {
    private Writer writer;
    private CommandFactory factory;
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public ConsoleCommandReader(CommandFactory factory, Writer writer) {
        this.factory = factory;
        this.writer = writer;
    }

    @Override
    public Command readCommands() throws IOException {

        String input = bufferedReader.readLine();
        if (input == null) {
            writer.write("Enter something more meaningful");
            throw new NullPointerException();
        }

        List<String> commandParameters = new ArrayList<>();
        commandParameters.addAll(Arrays.asList(input.trim().toLowerCase().split("\\s+")));
        if (commandParameters.size() < 2) {
            commandParameters.add(null);
        }

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

    public User readUser() {
        User user = new User();
        isNewUser(user);
        readLogin(user);
        readPassword(user);

        return user;
    }

    private void isNewUser(User user) {
        try {
            writer.write("Do you want to register? yes/no");
            String login = bufferedReader.readLine().trim().toLowerCase();
            if (login.equals("yes")) {
                user.setNewUser(true);
            } else {
                user.setNewUser(false);
            }
        } catch (InvalidPersonFieldException | IOException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect input, try again: ");
            readLogin(user);
        }
    }
    private void readLogin(User user) {
        try {
            writer.write("Enter a login: ");
            String login = bufferedReader.readLine().trim().toLowerCase();
            user.setLogin(login);
        } catch (InvalidPersonFieldException | IOException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect input, try again: ");
            readLogin(user);
        }
    }

    private void readPassword(User user) {
        try {
            writer.write("Enter a password: ");
            String password = bufferedReader.readLine();
            user.setPassword(password);
        } catch (InvalidPersonFieldException | IOException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect input, try again: ");
            readPassword(user);
        }
    }
    @Override
    public Person readPerson() {
        Person person = new DefaultPerson();
        writer.write("Enter the new person's details below: ");
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
        writer.write("Select a country from the list: ");
        String[] countries = Country.returnCountries();

        for (String i : countries) {
            writer.write(i);
        }

        try {
            String input = bufferedReader.readLine().trim().toLowerCase();
            country = Country.switchCountry(input);
            if (country == null) throw new InvalidPersonFieldException("This country is not in the list");

        } catch (IOException | InvalidPersonFieldException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect input, try again: ");
            readCountry();
        }
        return country;
    }

    private void readName(Person person) {
        try {
            writer.write("Enter a name: ");
            String name = bufferedReader.readLine().trim().toLowerCase();
            person.setName(name);
        } catch (InvalidPersonFieldException | IOException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect input, try again: ");
            readName(person);
        }
    }

    private void readCoordinates(Person person) {
        try {
            Coordinates coordinates = new Coordinates();
            writer.write("Enter the coordinates of the person: ");
            writer.write("Coordinate X:");
            Integer x = Integer.valueOf(bufferedReader.readLine().trim());
            coordinates.setX(x);
            writer.write("Coordinate Y:");
            Integer y = Integer.valueOf(bufferedReader.readLine().trim());
            coordinates.setY(y);
            person.setCoordinates(coordinates);
        } catch(InvalidPersonFieldException | IOException | NumberFormatException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect input, try again: ");
            readCoordinates(person);
        }
    }

    private void readHeight(Person person) {
        try {
            writer.write("Enter the height of the person");
            Double height = Double.valueOf(bufferedReader.readLine().trim());
            person.setHeight(height);
        } catch (InvalidPersonFieldException | IOException | NumberFormatException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect input, try again: ");
            readHeight(person);
        }
    }

    private void readWeight(Person person) {
        try {
            writer.write("Enter the person's weight");
            Long weight = Long.valueOf(bufferedReader.readLine().trim());
            person.setWeight(weight);
        } catch (InvalidPersonFieldException | IOException | NumberFormatException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect input, try again: ");
            readWeight(person);
        }
    }

    private void readPassportId(Person person) {
        try {
            writer.write("Enter the person's passport details: ");
            String passportId = bufferedReader.readLine().trim();
            person.setPassportID(passportId);
        } catch (InvalidPersonFieldException | IOException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect input, try again: ");
            readPassportId(person);
        }
    }

    private void readNationality(Person person) {
        try {
            Country p;
            writer.write("Choose a person's country of birth from the suggested options: ");
            for (String i : Country.returnCountries()) {
                writer.write(i);
            }
            String input = bufferedReader.readLine().trim().toLowerCase();
            Country country = Country.switchCountry(input);
            if (country == null) {
                throw new InvalidPersonFieldException("This country is not in the list");
            }
            person.setNationality(country);
        } catch (InvalidPersonFieldException | IOException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect input, try again: ");
            readNationality(person);
        }
    }

    private void readLocation(Person person) {
        try {
            Location location = new Location();
            writer.write("Enter the coordinates and the name of the locality: ");
            writer.write("Coordinate X: ");
            Double x = Double.valueOf(bufferedReader.readLine().trim());
            location.setX(x);
            writer.write("Coordinate Y: ");
            double y = Double.valueOf(bufferedReader.readLine().trim());
            location.setY(y);
            writer.write("Enter the name of the locality: ");
            String name = bufferedReader.readLine().trim();
            location.setName(name);
            person.setLocation(location);
        } catch(InvalidPersonFieldException | IOException | NumberFormatException e) {
            writer.write(e.getMessage());
            writer.write("Incorrect input, try again: ");
            readLocation(person);
        }
    }
    private void setCreationDate(Person person) {
        ZoneId zoneId = ZoneId.of(Country.selectZoneId(person.getNationality()));
        person.setCreationDate(ZonedDateTime.now(zoneId));
    }
}
