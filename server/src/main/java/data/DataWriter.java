package data;

import person.Person;
import user.Auth;

public interface DataWriter {
    int addElement(Person person);
    void updateElement(Person worker, int id);
    void removeElement(int id);
    void addUser(String login, String password);
}
