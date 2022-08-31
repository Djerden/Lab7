package person;

import java.time.ZonedDateTime;

public interface Person {
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
