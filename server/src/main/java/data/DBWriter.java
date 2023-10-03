package data;

import person.Person;

import java.sql.*;
import java.time.LocalDate;

public class DBWriter implements DataWriter {

    private Connection connection;

    public DBWriter(Connection connection) {
        this.connection = connection;
    }
    @Override
    public int addElement(Person person) {
        try(PreparedStatement statement = connection.prepareStatement(
                "insert into persons (number, name, x_coordinate, y_coordinate, creation_date, height, weight, passport_id, " +
                        "nationality, x_location, y_location, name_location, login) values (?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?)")) {
            statement.setString(1, person.getNumber());
            statement.setString(2, person.getName());
            statement.setInt(3, person.getCoordinates().getX());
            statement.setInt(4, person.getCoordinates().getY());
            statement.setTimestamp(5, Timestamp.from(person.getCreationDate().toInstant()));
            statement.setDouble(6, person.getHeight());
            statement.setDouble(7, person.getWeight());
            statement.setString(8, person.getPassportID());
            statement.setString(9, person.getNationality().toString());

            statement.setDouble(10, person.getLocation().getX());
            statement.setDouble(11, person.getLocation().getY());
            statement.setString(12, person.getLocation().getName());
            statement.setString(13, person.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Запись insert " + e.getMessage());
        }

        int id = 0;
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select id from persons where number = '" + person.getNumber() + "' AND name = '" + person.getName() + "'");
            while(rs.next()) {
                id = rs.getInt(1);
            }
        } catch(SQLException e) {
            System.out.println("Ошибка с получением id из бд");

        }


        return id; // заменить на id, который присвоит бд
    }

    @Override
    public void updateElement(Person person, int id) {
        try(PreparedStatement statement = connection.prepareStatement("update persons set number = ?, name = ?, " +
                        "x_coordinate = ?, y_coordinate = ?, creation_date = ?, height = ?, weight = ?, passport_id = ?, nationality = ?, " +
                "x_location = ?, y_location = ?, name_location = ?, login = ? where id = ?")) {
            statement.setString(1, person.getNumber());
            statement.setString(2, person.getName());
            statement.setInt(3, person.getCoordinates().getX());
            statement.setInt(4, person.getCoordinates().getY());
            statement.setTimestamp(5, Timestamp.from(person.getCreationDate().toInstant())); //person.getCreationDate()
            statement.setDouble(6, person.getHeight());
            statement.setDouble(7, person.getWeight());
            statement.setString(8, person.getPassportID());
            statement.setString(9, person.getNationality().toString());

            statement.setDouble(10, person.getLocation().getX());
            statement.setDouble(11, person.getLocation().getY());
            statement.setString(12, person.getLocation().getName());
            statement.setString(13, person.getLogin());
            statement.setInt(14, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("update не удался в бд");
        }
    }

    @Override
    public void removeElement(int id) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("delete from persons where id = " +id);
        } catch(SQLException e) {
            System.out.println("Обьект не удален из бд");
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(String login, String password ) {
        try(PreparedStatement statement = connection.prepareStatement(
                "insert into users (login, password) values (?, ?)")) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }
}
