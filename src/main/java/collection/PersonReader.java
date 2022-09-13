package collection;

import person.Person;

import java.util.Map;

/**
 * Interface for reading collection of Persons from file
 */
public interface PersonReader {
    Map<String, Person> readPersons();
}
