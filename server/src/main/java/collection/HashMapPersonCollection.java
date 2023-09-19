package collection;

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
//    private PersonReader personReader;
//    private PersonWriter personWriter;


    public HashMapPersonCollection() { //PersonReader personReader, PersonWriter personWriter
        personCollection = new HashMap<>();
        usersCollection = new HashMap<>();
        creationDate = ZonedDateTime.now();
//        this.personReader = personReader;
//        this.personWriter = personWriter;
    }

    public String addNewUser(String login, String password) {
        if (usersCollection.containsKey(login)) {
            return "User with this login already exists";
        } else {
            usersCollection.put(login, password);
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
        return "Сollection type: Map of persons" +
                "\nCreation date: " + creationDate +
                "\nNumber of elements: " + personCollection.size();
    }

    @Override
    public List<Person> show() {
        List<Person> tempList = new ArrayList<>();
        personCollection.values().stream().sorted().forEach(tempList::add);
        return tempList;
    }

    @Override
    public void insert(String key, Person person) {
        // Ключ - номер телефона
        personCollection.put(key, person);
    }


    @Override
    public void update(int id, Person newPerson) {
        for (Map.Entry<String, Person> entry : personCollection.entrySet()) {
            if (entry.getValue().getId() == id) {
                newPerson.setId(id);
                personCollection.put(entry.getKey(), newPerson);
                break;
            }
        }
    }

    @Override
    public void remove_key(String key) {
        DefaultPerson.removeId((DefaultPerson) personCollection.remove(key));
    }

    @Override
    public void clear() {
        personCollection.clear();
    }

//    @Override
//    public void save() {
//        personWriter.writePersons(personCollection);
//    }

//    @Override
//    public void loadData() {
//            personCollection = personReader.readPersons();
//    }


    @Override
    public void remove_greater(Person person) {
        for (Map.Entry<String, Person> entry : personCollection.entrySet()) {
            if (person.compareTo(entry.getValue()) > 0) {
                System.out.println(entry.getKey() + " " + entry.getValue().getName() + " - удален");
                DefaultPerson.removeId((DefaultPerson)personCollection.remove(entry.getKey()));
            }
        }
    }


    @Override
    public void remove_greater_key(String key) {
        try {
            for (String mapKey : personCollection.keySet()) {
                if (Integer.valueOf(mapKey) > Integer.valueOf(key)) {
                    DefaultPerson.removeId((DefaultPerson) personCollection.remove(mapKey));
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public Person remove_any_by_nationality(Country country) {
        if (!personCollection.isEmpty()) {
            for (Map.Entry<String, Person> entry : personCollection.entrySet()) {
                if (entry.getValue().getNationality().equals(country)) {
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
        /*
        String maxKey = "";
        Long maxWeight = 0L;
        for (Map.Entry<String, Person> entry : personCollection.entrySet()) {
            if (entry.getValue().getWeight() > maxWeight) {
                maxWeight = entry.getValue().getWeight();
                maxKey = entry.getKey();
            }
        }
        return personCollection.get(maxKey);
         */

        Optional<Person> maxWeightPerson = personCollection.values().stream().max(new WeightComparator());
        if (maxWeightPerson.isPresent()) {
            return maxWeightPerson.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Person> filter_less_than_passport_id(String passportId) {
        /*
        List<Person> tempList = new ArrayList<>();
        for (Person p : personCollection.values()) {
            if (p.getPassportID().compareTo(passportId) < 0) {
                tempList.add(p);
            }
        }
        return tempList;
         */

        List<Person> tempList = personCollection.values().stream()
                .filter(person -> person.getPassportID().compareTo(passportId) < 0)
                .collect(Collectors.toList());
        return tempList;
    }
    @Override
    public boolean isEmpty() {
        return personCollection.isEmpty();
    }


}
