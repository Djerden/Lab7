package person;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Comparator;

/**
 * Abstract implementation of the Person interface containing the necessary fields
 */
public abstract class AbstractPerson implements Person, Serializable {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double height; //Поле может быть null, Значение поля должно быть больше 0
    private Long weight; //Поле может быть null, Значение поля должно быть больше 0
    private String passportID; //Длина строки не должна быть больше 47, Значение этого поля должно быть уникальным, Длина строки должна быть не меньше 8, Поле может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле может быть null

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int compareTo(Person o) {
        // https://overcoder.net/q/27595/%D0%BA%D0%B0%D0%BA-%D1%81%D1%80%D0%B0%D0%B2%D0%BD%D0%B8%D0%B2%D0%B0%D1%82%D1%8C-%D0%BE%D0%B1%D1%8A%D0%B5%D0%BA%D1%82%D1%8B-%D0%BF%D0%BE-%D0%BD%D0%B5%D1%81%D0%BA%D0%BE%D0%BB%D1%8C%D0%BA%D0%B8%D0%BC-%D0%BF%D0%BE%D0%BB%D1%8F%D0%BC
        return Comparator.comparing(Person::getName).thenComparing(Person::getHeight)
                .thenComparing(a->a.getWeight()).thenComparing(Person::getCreationDate).compare(this, o);
    }

    @Override
    public String toString() {
        return "---------------" + "\n" +
                "name: " + name + "\n" +
                "id: " + id + "\n" +
                "coordinates: " + coordinates + "\n" +
                "creationDate: " + creationDate + "\n" +
                "height: " + height + "\n" +
                "weight: " + weight + "\n" +
                "passportID: " + passportID + "\n" +
                "nationality: " + nationality + "\n" +
                "location: " + location + "\n" +
                "---------------" + "\n";
    }
}
