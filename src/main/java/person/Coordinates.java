package person;

import exceptions.InvalidPersonFieldException;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Integer x; //Значение поля должно быть больше -199, Поле не может быть null
    private Integer y; //Поле не может быть null

    public Coordinates() {

    }

    public Coordinates(Integer x, Integer y) {
        setX(x);
        setY(y);
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        if (x == null || x <= -199) throw new InvalidPersonFieldException("Координата X не может быть null или быть меньше -199");
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        if (y == null) throw new InvalidPersonFieldException("Координата Y не может быть null");
        this.y = y;
    }

    @Override
    public String toString() {
        return "x: " + x +
                ", y: " + y;
    }
}
