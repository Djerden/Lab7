package data;

import person.Person;

import java.util.Map;

public interface DataReader {
    Map<String, Person> readElements();
    void getElement(long id);
    Map<String, String> readUsers();
    Person getLastElement();
    //Set<Organization> getOrganizations();
}
