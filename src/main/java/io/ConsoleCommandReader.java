package io;

import client.CommandFactory;
import client.CommandSimpleFactory;
import commands.Command;
import commands.ObjectArgCommand;
import commands.SimpleArgCommand;
import exceptions.AbsenceArgumentException;
import exceptions.InvalidPersonFieldException;
import exceptions.NoInputException;
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
            writer.write("Введите что-то более осмысленное");
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

    @Override
    public Person readPerson() {
        Person person = new DefaultPerson();
        writer.write("Введите данные нового человека ниже: ");
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




    //
    // Протестировать этот метод и по возможности объединить его с readNationality()
    //
    @Override
    public Country readCountry() {
        Country country = null;
        writer.write("Выберите страну из списка: ");
        String[] countries = Country.returnCountries();

        for (String i : countries) {
            writer.write(i);
        }

        try {
            String input = bufferedReader.readLine().trim().toLowerCase();
            country = Country.switchCountry(input);
            if (country == null) throw new InvalidPersonFieldException("Данной страны нет в списке");

        } catch (IOException | InvalidPersonFieldException e) {
            writer.write(e.getMessage());
            writer.write("Некорректный ввод, попробуйте еще раз: ");
            readCountry();
        }
        return country;
    }

    private void readName(Person person) {
        try {
            writer.write("Введите имя: ");
            String name = bufferedReader.readLine().trim().toLowerCase();
            person.setName(name);
        } catch (InvalidPersonFieldException | IOException e) {
            writer.write(e.getMessage());
            writer.write("Некорректный ввод, попробуйте еще раз: ");
            readName(person);
        }
    }

    private void readCoordinates(Person person) {
        try {
            Coordinates coordinates = new Coordinates();
            writer.write("Введите координаты человека: ");
            writer.write("Координата X:");
            Integer x = Integer.valueOf(bufferedReader.readLine().trim());
            coordinates.setX(x);
            writer.write("Координата Y:");
            Integer y = Integer.valueOf(bufferedReader.readLine().trim());
            coordinates.setY(y);
            person.setCoordinates(coordinates);
        } catch(InvalidPersonFieldException | IOException | NumberFormatException e) {
            writer.write(e.getMessage());
            writer.write("Некорректный ввод, попробуйте еще раз: ");
            readCoordinates(person);
        }
    }

    private void readHeight(Person person) {
        try {
            writer.write("Введите рост человека");
            Double height = Double.valueOf(bufferedReader.readLine().trim());
            person.setHeight(height);
        } catch (InvalidPersonFieldException | IOException | NumberFormatException e) {
            writer.write(e.getMessage());
            writer.write("Некорректный ввод, попробуйте еще раз: ");
            readHeight(person);
        }
    }

    private void readWeight(Person person) {
        try {
            writer.write("Введите Вес человека");
            Long weight = Long.valueOf(bufferedReader.readLine().trim());
            person.setWeight(weight);
        } catch (InvalidPersonFieldException | IOException | NumberFormatException e) {
            writer.write(e.getMessage());
            writer.write("Некорректный ввод, попробуйте еще раз: ");
            readWeight(person);
        }
    }

    private void readPassportId(Person person) {
        try {
            writer.write("Введите паспортные данные человека: ");
            String passportId = bufferedReader.readLine().trim();
            person.setPassportID(passportId);
        } catch (InvalidPersonFieldException | IOException e) {
            writer.write(e.getMessage());
            writer.write("Некорректный ввод, попробуйте еще раз: ");
            readPassportId(person);
        }
    }

    private void readNationality(Person person) {
        try {
            Country p;
            writer.write("Выберите страну рождения человека из предложенных вариантов: ");
            for (String i : Country.returnCountries()) {
                writer.write(i);
            }
            String input = bufferedReader.readLine().trim().toLowerCase();
            Country country = Country.switchCountry(input);
            if (country == null) {
                throw new InvalidPersonFieldException("Данной страны нет в списке");
            }
            person.setNationality(country);
        } catch (InvalidPersonFieldException | IOException e) {
            writer.write(e.getMessage());
            writer.write("Некорректный ввод, попробуйте еще раз: ");
            readNationality(person);
        }
    }

    private void readLocation(Person person) {
        try {
            Location location = new Location();
            writer.write("Введите координаты и имя населенного пункта: ");
            writer.write("Координата X: ");
            Double x = Double.valueOf(bufferedReader.readLine().trim());
            location.setX(x);
            writer.write("Координата Y: ");
            double y = Double.valueOf(bufferedReader.readLine().trim());
            location.setY(y);
            writer.write("Введите название населенного пункта: ");
            String name = bufferedReader.readLine().trim();
            location.setName(name);
            person.setLocation(location);
        } catch(InvalidPersonFieldException | IOException | NumberFormatException e) {
            writer.write(e.getMessage());
            writer.write("Некорректный ввод, попробуйте еще раз: ");
            readLocation(person);
        }
    }
    private void setCreationDate(Person person) {
        ZoneId zoneId = ZoneId.of(Country.selectZoneId(person.getNationality()));
        person.setCreationDate(ZonedDateTime.now(zoneId));
    }
}
