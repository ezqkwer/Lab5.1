package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

//try-with-resources блок для безопасного открытия потока для записи в файл и
// автоматического его закрытия после завершения записи.


/**
 * Класс для работы с файлом CSV.
 */
public class FileManager {     //чтение и запись данных о городах в CSV
    private final String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }



    public List<City> readCitiesFromCSV() throws IOException {
        List<City> cities = new ArrayList<>();        // хранения объектов City
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));  //канал для чтения байтов из файла
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {    // построчное чтение текста из файла

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue; // Пропускаем пустые строки и комментарии
                String[] fields = line.split(",");   //разделяем строку по , и сохраняем поля в массив
                if (fields.length != 12) {
                    System.out.println("Ошибка формата строки CSV: " + line + ". Строка пропущена.");
                    continue;
                }
                try {
                    Long id = Long.parseLong(fields[0]);  // Читаем ID (поле 0) и преобразуем в число
                    String name = fields[1];
                    Long coordinatesX = Long.parseLong(fields[2]);
                    int coordinatesY = Integer.parseInt(fields[3]);
                    Date creationDate = new Date(Long.parseLong(fields[4])); // Читаем дату создания и преобразуем в дату
                    int area = Integer.parseInt(fields[5]);
                    Long population = Long.parseLong(fields[6]);
                    Float metersAboveSeaLevel = fields[7].isEmpty() ? null : Float.parseFloat(fields[7]); // Читаем высоту, может быть пустой
                    int timezone = Integer.parseInt(fields[8]);
                    long carCode = Long.parseLong(fields[9]);
                    Climate climate = Climate.valueOf(fields[10]);
                    Long governorAge = fields[11].isEmpty() ? null : Long.parseLong(fields[11]);

                    Coordinates coordinates = new Coordinates(coordinatesX, coordinatesY);
                    Human governor = new Human(governorAge);
                    City city = new City(name, coordinates, creationDate, area, population, metersAboveSeaLevel,
                            timezone, carCode, climate, governor);

                    city.setId(id); // Устанавливаем ID из файла
                    cities.add(city); // добавление созданного объекта City в список cities

                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка обработки строки CSV: " + line + ". " + e.getMessage() + ". Строка пропущена.");
                }
            }
        }
        return cities;
    }

    public void saveCitiesToCSV(LinkedHashSet<City> cityCollection) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (City city : cityCollection) {
                writer.println(city.toCSVString());
            }
        }
    }
}