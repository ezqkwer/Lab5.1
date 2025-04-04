package org.example;
import java.util.Date;


/**
 * Класс, описывающий город.
 */
public class City implements Comparable<City>{
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int area; //Значение поля должно быть больше 0
    private Long population; //Значение поля должно быть больше 0, Поле не может быть null
    private Float metersAboveSeaLevel;
    private int timezone; //Значение поля должно быть больше -13, Максимальное значение поля: 15
    private long carCode; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
    private Climate climate; //Поле не может быть null
    private Human governor; //Поле не может быть null

    // при добавлении нового города пользователем
    public City(String name, Coordinates coordinates, int area, Long population, Float metersAboveSeaLevel, int timezone,
                long carCode, Climate climate, Human governor) {
        this.id = generateId();

        if (name==null) {
            throw new NullPointerException("Имя города не может быть null");}
        this.name = name;
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Имя города не может быть пустым");}

        if (coordinates==null) {
            throw new NullPointerException("Координаты не могут быть null");}
        this.coordinates = coordinates;

        this.creationDate = new Date();

        this.area = area;
        if (area <= 0) {
            throw new IllegalArgumentException("Площадь города должна быть больше 0");}

        if (population==null) {
            throw new IllegalArgumentException("Население не может быть null");}
        this.population = population;
        if (population <= 0) {
            throw new IllegalArgumentException("Население города должно быть больше 0");}

        this.metersAboveSeaLevel = metersAboveSeaLevel;

        this.timezone = timezone;
        if (timezone <= -14 || timezone > 15) {
            throw new IllegalArgumentException("Значение должно быть в диапазоне от -13 до 15");}

        this.carCode = carCode;
        if (carCode <= 0 || carCode > 1000) {
            throw new IllegalArgumentException("CarCode должен быть в диапазоне от 1 до 1000");}

        if (climate==null) {
            throw new IllegalArgumentException("Климат не может быть null");}
        this.climate = climate;

        if (governor==null){
            throw new IllegalArgumentException("Губернатор не может быть null");}
        this.governor = governor;
    }

    // при чтении данных из CSV
    public City(String name, Coordinates coordinates, Date creationDate, int area, Long population,
                Float metersAboveSeaLevel, int timezone, long carCode, Climate climate, Human governor) {
        this.id = generateId(); // id будет сгенерирован, но потом может быть перезаписан при загрузке из файла

        if (creationDate==null) {
            throw new IllegalArgumentException("Дата создания не может быть null");}
        this.creationDate = creationDate;

        if (name==null) {
            throw new NullPointerException("Имя города не может быть null");}
        this.name = name;
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Имя города не может быть пустым");}

        if (coordinates==null) {
            throw new NullPointerException("Координаты не могут быть null");}
        this.coordinates = coordinates;

        if (population==null) {
            throw new IllegalArgumentException("Население не может быть null");}
        this.population = population;
        if (population <= 0) {
            throw new IllegalArgumentException("Население города должно быть больше 0");}

        this.metersAboveSeaLevel = metersAboveSeaLevel;

        this.timezone = timezone;
        if (timezone <= -14 || timezone > 15) {
            throw new IllegalArgumentException("Часовой пояс должен быть в диапазоне от -13 до 15");}

        this.carCode = carCode;
        if (carCode <= 0 || carCode > 1000) {
            throw new IllegalArgumentException("CarCode должен быть в диапазоне от 1 до 1000");}

        if (climate==null) {
            throw new IllegalArgumentException("Климат не может быть null");}
        this.climate = climate;

        if (governor==null){
            throw new IllegalArgumentException("Губернатор не может быть null");}
        this.governor = governor;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID должен быть больше 0 и не null");}
        this.id = id;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        if (creationDate==null) {
            throw new IllegalArgumentException("Дата создания не может быть null");}
        this.creationDate = creationDate;
    }

    public int getArea() {
        return area;
    }


    public int getTimezone() {
        return timezone;
    }

        public long getCarCode() {
        return carCode;
    }

    public static void resetNextId() {
        nextId = 1;
    }

    private static long nextId = 1; // Статическая переменная для генерации ID
    private static synchronized Long generateId() {
        return nextId++;
    }

    @Override
    public int compareTo(City other) {
        return this.name.compareTo(other.name); // Сортировка по умолчанию по имени города
    }

    @Override
    public String toString() {
        return "City{" +
                "id= " + id +
                ", name= '" + name + '\'' +
                ", coordinates= " + coordinates +
                ", creationDate= " + creationDate +
                ", area= " + area +
                ", population= " + population +
                ", metersAboveSeaLevel= " + metersAboveSeaLevel +
                ", timezone= " + timezone +
                ", carCode= " + carCode +
                ", climate= " + climate +
                ", governor= " + governor +
                '}';
    }

    public String toCSVString() {
        return id + "," +
                name + "," +
                coordinates.getX() + "," +
                coordinates.getY() + "," +
                creationDate.getTime() + "," + // Сохраняем timestamp для Date
                area + "," +
                population + "," +
                (metersAboveSeaLevel == null ? "" : metersAboveSeaLevel) + "," +
                timezone + "," +
                carCode + "," +
                climate + "," +
                (governor.getAge() == null ? "" : governor.getAge());
    }

    public void setCarCode(long carCode) {
        if (carCode <= 0 || carCode > 1000) {
            throw new IllegalArgumentException("CarCode должен быть в диапазоне от 1 до 1000");}
        this.carCode = carCode;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        if (climate==null) {
            throw new IllegalArgumentException("Климат не может быть null");}
        this.climate = climate;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) {
        if (governor==null){
            throw new IllegalArgumentException("Губернатор не может быть null");}
        this.governor = governor;
    }

    public void setTimezone(int timezone) {
        if (timezone <= -14 || timezone > 15) {
            throw new IllegalArgumentException("Часовой пояс должен быть в диапазоне от -13 до 15");}
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {    //при возможном изменении значения поля
        if (name==null) {
            throw new NullPointerException("Имя города не может быть null");}
        this.name = name;
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Имя города не может быть пустым");}
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates==null) {
            throw new NullPointerException("Координаты не могут быть null");}
        this.coordinates = coordinates;
    }

    public void setArea(int area) {
        if (area <= 0) {
            throw new IllegalArgumentException("Площадь города должна быть больше 0");}
        this.area = area;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        if (population==null) {
            throw new IllegalArgumentException("Население не может быть null");}
        this.population = population;
        if (population <= 0) {
            throw new IllegalArgumentException("Население города должно быть больше 0");
        }
    }

    public Float getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(Float metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

}
