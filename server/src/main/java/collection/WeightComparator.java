package collection;

import person.Person;

import java.util.Comparator;

public class WeightComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getWeight() > o2.getWeight() ? 1 : o1.getWeight() < o2.getWeight() ? -1 : 0;
    }
}
