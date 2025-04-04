package org.example;


/**
 * Класс, описывающий координаты.
 */
public class Coordinates {
    private Long x; //Максимальное значение поля: 588, Поле не может быть null
    private int y; //Максимальное значение поля: 59

    public Coordinates(Long x, int y) {
        if (x == null) {
            throw new NullPointerException("Координата X не может быть null");
        }
        this.x = x;

        if (x > 588) {
            throw new IllegalArgumentException("Координата X не может быть больше 588");
        }
        this.y = y;
        if (y > 59) {
            throw new IllegalArgumentException("Координата Y не может быть больше 59");
        }
    }

    public Long getX() {
        return x;
    }

    public int getY() {
        return y;
    }

        @Override
    public String toString() {
        return "Coordinates{" +
                "x= " + x + ", y= " + y + '}';
    }

}
