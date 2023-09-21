package data;

import person.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class DBManager implements DataManager {

    private final DataReader dataReader;
    private final DataWriter dataWriter;

    public DBManager(String url, String user, String pass) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, pass);
        this.dataReader = new DBReader(connection);
        this.dataWriter = new DBWriter(connection);
    }
    @Override
    public Map<String, Person> readCollection() {
        return dataReader.readElements();
    }

    @Override
    public int addElement(Person person) {
        return dataWriter.addElement(person);
    }

    @Override
    public void updateElement(Person person, int id) {
        dataWriter.updateElement(person, id);
    }

    @Override
    public void removeElement(int id) {
        dataWriter.removeElement(id);
    }

    @Override
    public void addUser(String login, String password) {
        dataWriter.addUser(login, password);
    }

    @Override
    public Map<String, String> readUsers() {

        return dataReader.readUsers();
    }
}
