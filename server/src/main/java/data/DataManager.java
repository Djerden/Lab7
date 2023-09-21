package data;

import person.Person;

import java.util.Map;

public interface DataManager {
    Map<String, Person> readCollection();
    int addElement(Person person);

    void updateElement(Person person, int id);

    void removeElement(int id);

    void addUser(String login, String password);

    Map<String, String> readUsers();
}
