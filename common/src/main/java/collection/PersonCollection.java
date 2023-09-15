package collection;

import person.Country;
import person.Person;

import java.util.List;

/**
 * Interface that provides the necessary functionality for collection
 */
public interface PersonCollection {

    String info(); // вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
    List<Person> show(); // вывести в стандартный поток вывода все элементы коллекции в строковом представлении
    void insert(String key, Person person); // добавить новый элемент с заданным ключом
    void update(int id, Person newPerson); // обновить значение элемента коллекции, id которого равен заданному
    void remove_key(String key); // удалить элемент из коллекции по его ключу
    void clear(); // очистить коллекцию
//    void save(); // сохранить коллекцию в файл
//    void loadData(); // загрузить данные из файла
    void remove_greater(Person person); // удалить из коллекции все элементы, превышающие заданный
    void remove_greater_key(String key); // удалить из коллекции все элементы, ключ которых превышает заданный
    Person remove_any_by_nationality(Country country); // удалить из коллекции один элемент, значение поля nationality которого эквивалентно заданному
    Person max_by_weight(); // вывести любой объект из коллекции, значение поля weight которого является максимальным
    List<Person> filter_less_than_passport_id(String passportId); // вывести элементы, значение поля passportID которых меньше заданного

    boolean isEmpty();

}
