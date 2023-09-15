package person;

import exceptions.InvalidPersonFieldException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of objects of type Person
 */
public class DefaultPerson extends AbstractPerson {
    /* Значение поля id должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
     Поле name не может быть null, Строка не может быть пустой
     Поле coordinates не может быть null
     Поле creationDate не может быть null, Значение этого поля должно генерироваться автоматически
     Поле height может быть null, Значение поля должно быть больше 0
     Поле weight может быть null, Значение поля должно быть больше 0
     Длина строки passportID не должна быть больше 47, Значение этого поля должно быть уникальным, Длина строки должна быть не меньше 8, Поле может быть null
     Поле nationality не может быть null
     Поле location может быть null*/

    private static int nextId = 1;

    public static List<Integer> removedIds = new ArrayList<>();

    public DefaultPerson() {
        setAutomaticallyId();
    }
    public DefaultPerson(String name, Coordinates coordinates, Double height, Long weight,
                         String passportId, Country nationality, Location location) {

        setName(name);
        setCoordinates(coordinates);
        setHeight(height);
        setWeight(weight);
        setPassportID(passportId);
        setNationality(nationality);
        setLocation(location);

        // Установка даты создания
        ZoneId zoneId = ZoneId.of(Country.selectZoneId(this.getNationality()));
        setCreationDate(ZonedDateTime.now(zoneId));

        // Установка id
        setAutomaticallyId();
    }

    private void setAutomaticallyId() {
        if (removedIds.isEmpty()) {
            super.setId(nextId);
            nextId++;
        } else {
            super.setId(removedIds.remove(0));
        }
    }

    public static void removeId(Person person) {
        removedIds.add(person.getId());
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().length() <= 0) throw new InvalidPersonFieldException("The name field cannot be null or empty");
        super.setName(name);
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new InvalidPersonFieldException("The coordinates field cannot be null");
        super.setCoordinates(coordinates);
    }

    @Override
    public void setCreationDate(ZonedDateTime creationDate) {
        super.setCreationDate(creationDate);

        // Сделать автоматическую реализацию
    }

    @Override
    public void setHeight(Double height) {
        if (height != null && height <= 0) throw new InvalidPersonFieldException("The height field must be greater than 0");
        super.setHeight(height);
    }

    @Override
    public void setWeight(Long weight) {
        if (weight != null && weight <= 0) throw new InvalidPersonFieldException("The weight field must be greater than 0");
        super.setWeight(weight);
    }

    @Override
    public void setPassportID(String passportID) {
        if (passportID != null && (passportID.trim().length() < 8 || passportID.trim().length() > 47)) {
            throw new InvalidPersonFieldException("The length of the passportID string must not be less than 8 and greater than 47");
        }
        super.setPassportID(passportID.trim());
    }

    @Override
    public void setNationality(Country nationality) {
        if (nationality == null) throw new InvalidPersonFieldException("The nationality field cannot be null");
        super.setNationality(nationality);
    }


}
