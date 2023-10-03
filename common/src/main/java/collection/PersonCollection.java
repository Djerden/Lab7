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
    String insert(String key, Person person); // добавить новый элемент с заданным ключом
    String update(int id, Person newPerson, String login); // обновить значение элемента коллекции, id которого равен заданному
    String remove_key(String key, String login); // удалить элемент из коллекции по его ключу
    void clear(String login); // очистить коллекцию
//    void save(); // сохранить коллекцию в файл
//    void loadData(); // загрузить данные из файла
    String remove_greater(Person person, String login); // удалить из коллекции все элементы, превышающие заданный
    void remove_greater_key(String key, String login); // удалить из коллекции все элементы, ключ которых превышает заданный
    Person remove_any_by_nationality(Country country, String login); // удалить из коллекции один элемент, значение поля nationality которого эквивалентно заданному
    Person max_by_weight(); // вывести любой объект из коллекции, значение поля weight которого является максимальным
    List<Person> filter_less_than_passport_id(String passportId); // вывести элементы, значение поля passportID которых меньше заданного

    boolean isEmpty();

    String addNewUser(String login, String password);

    String checkUser(String login, String password);
}
