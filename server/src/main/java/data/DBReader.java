package data;

import person.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class DBReader implements DataReader {

    private Connection connection;

    public DBReader(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Map<String, Person> readElements() {
        Map<String, Person> personCollection = new HashMap<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from persons");
            while(rs.next()) {
                Person person = createPerson(rs);
                personCollection.put(person.getNumber(), person);
            }
        } catch(SQLException e) {
            System.out.println("Данные не были загружены " + e.getMessage());
        }
        return personCollection;
    }

    @Override
    public void getElement(long id) {

    }

    @Override
    public Map<String, String> readUsers() {
        Map<String, String> usersCollection = new HashMap<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from users");
            while(rs.next()) {
                usersCollection.put(rs.getString("login"), rs.getString("password"));
            }
        } catch(SQLException e) {

        }
        return usersCollection;
    }

    @Override
    public Person getLastElement() {
        return null;
    }

    private Person createPerson(ResultSet rs) throws SQLException {
        Person person = new DefaultPerson();
        person.setId(rs.getInt(1)); //id
        person.setNumber(rs.getString(2));//number
        person.setName(rs.getString(3)); //name
        person.setCoordinates(createCoordianates(rs));
        person.setCreationDate(rs.getTimestamp(6).toInstant().atZone(ZoneId.systemDefault()));//"creation_date"
        person.setHeight(rs.getDouble(7)); //"height"
        person.setWeight((long) rs.getInt(8));//"weight"
        person.setPassportID(rs.getString(9));//"passport_id"
        person.setNationality(Country.switchCountry(rs.getString(10)));//"nationality"

        person.setLocation(createLocation(rs));
        person.setLogin(rs.getString(14));//"login"

        return person;
    }

    private Coordinates createCoordianates(ResultSet rs) throws SQLException {
        Coordinates coordinates = new Coordinates();
        coordinates.setX(rs.getInt(4));//"x_coordinate"
        coordinates.setY(rs.getInt(5));//
        return coordinates;
    }

    private Location createLocation(ResultSet rs) throws SQLException {
        Location location = new Location();
        location.setX(rs.getDouble(11));//"x_location"
        location.setY(rs.getDouble(12));//"y_location"
        location.setName(rs.getString(13));//"name_location"
        return location;
    }
}
