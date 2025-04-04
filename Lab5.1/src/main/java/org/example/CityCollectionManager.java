package org.example;

import java.io.IOException;
import java.util.*;

/**
 * Класс для управления коллекцией городов.
 */
public class CityCollectionManager {
    private final LinkedHashSet<City> cityCollection;    //хранение элементов в порядке добавления и уникальность
    private final Date initializationDate;
    private final HistoryManager historyManager;

    public CityCollectionManager() {
        this.cityCollection = new LinkedHashSet<>();
        this.initializationDate = new Date();
        this.historyManager = new HistoryManager();
    }


    public HistoryManager getHistoryManager() {
        return historyManager;
    }


    public void loadCollectionFromFile(FileManager fileManager) throws IOException {
        cityCollection.clear();   //чтоб не осталось старых данных
        List<City> cities = fileManager.readCitiesFromCSV();     //список городов, прочитанных из CSV файла
        for (City city : cities) {
            addCity(city);       //добавление городов коллекцию
        }
    }


    public void saveCollectionToFile(FileManager fileManager) throws IOException {
        fileManager.saveCitiesToCSV(cityCollection);
    }


    public void addCity(City city) {
        cityCollection.add(city);
    }


    public boolean updateCity(Long id, City updatedCity) {
        for (City city : cityCollection) {
            if (city.getId().equals(id)) {    //совпадает ли ID текущего города с ID, который был передан в метод в качестве параметра (id).
                updatedCity.setId(id); // Сохраняем ID
                updatedCity.setCreationDate(city.getCreationDate()); // Сохраняем дату создания
                cityCollection.remove(city);  //удаляем старый город
                cityCollection.add(updatedCity);   //новый город с новыми данными
                return true;   //город обновлен
            }
        }
        return false; //город с указанным ID не был найден в коллекции, и обновление не произошло
    }


    public boolean removeCityById(Long id) {
        return cityCollection.removeIf(city -> city.getId().equals(id));
    }


    public void clearCollection() {
        cityCollection.clear();
        City.resetNextId();
    }



    // 0 - city, сравнение идет с С
    public void removeGreater(City city) {
        cityCollection.removeIf(c -> c.compareTo(city) > 0);
    }


    public void removeLower(City city) {
        cityCollection.removeIf(c -> c.compareTo(city) < 0);
    }


    public long sumOfCarCode() {
        return cityCollection.stream().mapToLong(City::getCarCode).sum();
    }


    public double averageOfTimezone() {
        if (cityCollection.isEmpty()) {
            return 0;
        }
        return cityCollection.stream().mapToInt(City::getTimezone).average().orElse(0);
    }


    public City minByArea() {
        return cityCollection.stream().min(Comparator.comparingInt(City::getArea)).orElse(null);
    }


    public String getCollectionInfo() {
        return "Тип коллекции: " + cityCollection.getClass().getSimpleName() + "\n" +
                "Дата инициализации: " + initializationDate + "\n" +
                "Количество элементов: " + cityCollection.size();
    }


    public String showCollection() {
        if (cityCollection.isEmpty()) {
            return "Коллекция пуста.";
        }
        StringBuilder sb = new StringBuilder();
        for (City city : cityCollection) {
            sb.append(city).append("\n");
        }
        return sb.toString();
    }


    public boolean exists(Long id) {
        for (City city : cityCollection) {
            if (city.getId().equals(id)) {
                return true; // Город с таким ID найден, возвращаем true
            }
        }
        return false;
    }
}