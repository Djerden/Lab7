package person;

import java.time.ZonedDateTime;

/**
 * Interface that provides basic functionality for objects of the Person type
 */
public interface Person extends Comparable<Person>{

    public String getLogin();

    public void setLogin(String login);
    public String getNumber();

    public void setNumber(String number);
    public int getId();

    public void setId(int id);

    public String getName();

    public void setName(String name);

    public Coordinates getCoordinates();

    public void setCoordinates(Coordinates coordinates);

    public ZonedDateTime getCreationDate();

    public void setCreationDate(ZonedDateTime creationDate);

    public Double getHeight();

    public void setHeight(Double height);

    public Long getWeight();

    public void setWeight(Long weight);

    public String getPassportID();

    public void setPassportID(String passportID);

    public Country getNationality();

    public void setNationality(Country nationality);

    public Location getLocation();

    public void setLocation(Location location);
}
