package collection;

import data.DataManager;
import person.Country;
import person.DefaultPerson;
import person.Person;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Specific implementation of the PersonCollection interface with data stored in hashmap
 */
public class HashMapPersonCollection implements PersonCollection {

    private Map<String, Person> personCollection;

    private Map<String, String> usersCollection; // login : password
    private ZonedDateTime creationDate;
    private DataManager dataManager;


    public HashMapPersonCollection(DataManager dataManager) { //PersonReader personReader, PersonWriter personWriter
        creationDate = ZonedDateTime.now();
        this.dataManager = dataManager;
        personCollection = dataManager.readCollection();
        usersCollection = dataManager.readUsers();
    }

    private void updateCollection() {
        personCollection = dataManager.readCollection();
        usersCollection = dataManager.readUsers();
    }
    public String addNewUser(String login, String password) {
        if (usersCollection.containsKey(login)) {
            return "User with this login already exists";
        } else {
            updateCollection();
            //usersCollection.put(login, password);
            dataManager.addUser(login, password);
            return "success";
        }
    }

    public String checkUser(String login, String password) {

        if (usersCollection.containsKey(login)) {
            if (usersCollection.get(login).equals(password)) {
                return "success";
            } else {
                return "password is not correct";
            }
        } else {
            return "user not found";
        }
    }

    @Override
    public String info() {
        updateCollection();
        return "Сollection type: Map of persons" +
                "\nCreation date: " + creationDate +
                "\nNumber of elements: " + personCollection.size();
    }

    @Override
    public List<Person> show() {
        updateCollection();
        List<Person> tempList = new ArrayList<>();
        personCollection.values().stream().sorted().forEach(tempList::add);
        return tempList;
    }

    @Override
    public String insert(String key, Person person) {
        // Ключ - номер телефона
        updateCollection();
        try {
            if (personCollection.containsKey(key)) {
                return "person already exists";
            } else {
                int id = dataManager.addElement(person);
                person.setId(id);
                personCollection.put(key, person);
            }
        } catch(Exception e) {
            System.out.println("error with db " + e.getMessage());
        }
        return "Person with number: " + key + " was added";
    }

    @Override
    public String update(int id, Person newPerson, String login) {
        updateCollection();
        for (Map.Entry<String, Person> entry : personCollection.entrySet()) {
            if (entry.getValue().getId() == id && entry.getValue().getLogin().equals(login)) {
                newPerson.setId(id);
                dataManager.updateElement(newPerson, id);
                personCollection.put(entry.getKey(), newPerson);
                return "Object with " + id + " have been updated";
            }
        }
        return "You don't have access to the object being replaced or the id is specified incorrectly";
    }

    @Override
    public String remove_key(String key, String login) {
        updateCollection();
        try {
            if (personCollection.get(key).getLogin().equals(login)) {
                dataManager.removeElement(personCollection.get(key).getId());
                personCollection.remove(key);
                return "The person with the number " + key + " deleted";
            } else {
                return "object wasn't deleted. Make sure that the object belongs to you";
            }
        } catch (NullPointerException e) {
            return "the number is incorrect";
        }
    }

    @Override
    public void clear(String login) {
        updateCollection();
        try {
            for (Map.Entry<String, Person> entry : personCollection.entrySet()) {
                if (entry.getValue().getLogin().equals(login)) {
                    dataManager.removeElement(entry.getValue().getId());
                    personCollection.remove(entry.getValue().getNumber());
                }
            }
        } catch (ConcurrentModificationException e) {
            clear(login);
        }
    }

    @Override
    public String remove_greater(Person person, String login) {
        updateCollection();
        for (Map.Entry<String, Person> entry : personCollection.entrySet()) {
            if (person.compareTo(entry.getValue()) > 0 && entry.getValue().getLogin().equals(login)) {
                dataManager.removeElement(entry.getValue().getId());
                personCollection.remove(entry.getKey());
                return entry.getKey() + " " + entry.getValue().getName() + " - удален";
            }
        }
        return "Not a single object has been deleted";
    }


    @Override
    public void remove_greater_key(String key, String login) {
        updateCollection();
        try {
            for (String mapKey : personCollection.keySet()) {
                if (Integer.valueOf(mapKey) > Integer.valueOf(key) && personCollection.get(mapKey).getLogin().equals(login)) {
                    dataManager.removeElement(personCollection.get(mapKey).getId());
                    personCollection.remove(mapKey);
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public Person remove_any_by_nationality(Country country, String login) {
        updateCollection();
        if (!personCollection.isEmpty()) {
            for (Map.Entry<String, Person> entry : personCollection.entrySet()) {
                if (entry.getValue().getNationality().equals(country) && entry.getValue().getLogin().equals(login)) {
                    dataManager.removeElement(entry.getValue().getId());
                    Person person = personCollection.remove(entry.getKey());
                    DefaultPerson.removeId((DefaultPerson) person);
                    return person;
                }
            }

        }
        return null;
    }

    @Override
    public Person max_by_weight() {
        updateCollection();
        Optional<Person> maxWeightPerson = personCollection.values().stream().max(new WeightComparator());
        if (maxWeightPerson.isPresent()) {
            return maxWeightPerson.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Person> filter_less_than_passport_id(String passportId) {
        updateCollection();
        List<Person> tempList = personCollection.values().stream()
                .filter(person -> person.getPassportID().compareTo(passportId) < 0)
                .collect(Collectors.toList());
        return tempList;
    }
    @Override
    public boolean isEmpty() {
        updateCollection();
        return personCollection.isEmpty();
    }


}
