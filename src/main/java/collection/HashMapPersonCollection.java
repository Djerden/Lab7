package collection;

import person.Country;
import person.Person;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapPersonCollection implements PersonCollection {

    private Map<String, Person> personCollection; // В качестве ключа я буду указывать номер телефона
    private ZonedDateTime creationDate;
    private PersonReader personReader;
    private PersonWriter personWriter;


    public HashMapPersonCollection(PersonReader personReader, PersonWriter personWriter) {
        personCollection = new HashMap<>();
        creationDate = ZonedDateTime.now();
        this.personReader = personReader;
        this.personWriter = personWriter;
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


    // Как обработать ситуацию, если будет указан несуществующий id - выбросить исключение
    // Также нужно сделать метод освобождения id и присвоения его новому владельцу
    @Override
    public void update(int id, Person newPerson) {
        String key = "invalid id";
        for (Map.Entry<String, Person> entry : personCollection.entrySet()) {
            if (entry.getValue().getId() == id) {
                key = entry.getKey();
                break;
            }
        }
        personCollection.put(key, newPerson);
    }

    @Override
    public void remove_key(String key) {
        personCollection.remove(key);
        // если удаляется, то нужно освободить id и дать возможность использовать его другими
        // сделать это
    }

    @Override
    public void clear() {
        personCollection.clear();
    }

    @Override
    public void save() {
        personWriter.writePersons(personCollection);
    }

    @Override
    public void loadData() {
        personCollection = (HashMap<String, Person>) personReader.readPersons();
    }

    // ЧТо то с ним не так, нужно разобраться с ним
    @Override
    public void remove_greater(Person person) {
        for (Map.Entry<String, Person> entry : personCollection.entrySet()) {
            if (person.compareTo(entry.getValue()) < 0) {
                System.out.println(entry.getKey() + " " + entry.getValue().getName());
                personCollection.remove(entry.getKey());
            }
        }
    }
    // обработка map с помощью стримов: https://java-blog.ru/collections/map-v-java-s-primerami

    // что-то с ним не так, разобраться в ошибке
    //
    //
    ///
    ///
    //
    //
    //
    //
    //
    //
    @Override
    public void remove_greater_key(String key) {
        for (String mapKey : personCollection.keySet()) {
            if (Integer.valueOf(mapKey) > Integer.valueOf(key)) {
                personCollection.remove(mapKey);
            }
        }
    }

    @Override
    public void remove_any_by_nationality(Country country) {
        for (Map.Entry<String, Person> entry : personCollection.entrySet()) {
            if (entry.getValue().getNationality().equals(country)) {
                personCollection.remove(entry.getKey());
                break;
            }
        }
    }

    @Override
    public Person max_by_weight() {
        String maxKey = "";
        Long maxWeight = 0L;
        for (Map.Entry<String, Person> entry : personCollection.entrySet()) {
            if (entry.getValue().getWeight() > maxWeight) {
                maxWeight = entry.getValue().getWeight();
                maxKey = entry.getKey();
            }
        }
        return personCollection.get(maxKey);
        // тоже неясно что вернет метод, если объектов не будет
        // вернет null и метод toString вызовет ошибку NullPointerException
    }

    @Override
    public List<Person> filter_less_than_passport_id(String passportId) {
        List<Person> tempList = new ArrayList<>();
        for (Person p : personCollection.values()) {
            if (p.getPassportID().compareTo(passportId) < 0) {
                tempList.add(p);
            }
        }
        return tempList;
    }

    // Протестировать сравнение цифр в строках, работает ли вообще? нужно ли переводить в Int?
}
