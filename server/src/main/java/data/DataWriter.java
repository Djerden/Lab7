package data;

import person.Person;
import user.Auth;

public interface DataWriter {
    void addElement(Person worker, Auth auth); //throws DBException;
    void updateElement(Person worker, int id, Auth auth); //throws DBException;
    void removeElement(long id, Auth auth); //throws DBException;
    void addUser(Auth auth); //throws DBException;
}
