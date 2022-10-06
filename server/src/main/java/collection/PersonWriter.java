package collection;

import person.Person;

import java.util.Map;

/**
 * Interface for writing collection of Persons in file
 */
public interface PersonWriter {
    void writePersons(Map<String, Person> persons);
}
