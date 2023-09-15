package person;

import exceptions.InvalidPersonFieldException;

import java.io.Serializable;

/**
 *  Сlass that defines the location of a locality
 */
public class Location implements Serializable {
    private Double x; //Поле не может быть null
    private double y;
    private String name; //Длина строки не должна быть больше 842, Поле не может быть null


    public Location() {

    }
    public Location(Double x, double y, String name) {

        setX(x);
        this.y = y;
        setName(name);
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        if (x == null) throw new InvalidPersonFieldException("The x coordinate cannot be null");
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() > 842) throw new InvalidPersonFieldException("The name field cannot be null, and its length is greater than 842");
        this.name = name;
    }

    @Override
    public String toString() {
        return "x: " + x +
                ", y: " + y +
                ", name: " + name;
    }
}
