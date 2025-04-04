package org.example;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Класс для обработки ввода пользователя.
 */
public class InputReader {
    private Scanner scanner;
    private boolean isNullInput = false; // Флаг для отслеживания ввода null

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readString(String prompt, boolean nullable) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (nullable && line.isEmpty()) {
                setNullInput(true); // Устанавливаем флаг, что был введен null
                return null;
            }
            if (!nullable && line.isEmpty()) {
                System.out.println("Поле не может быть пустым. Повторите ввод.");
                continue;
            }
            setNullInput(false); // Сбрасываем флаг, если ввод не null
            return line;
        }
    }

    public int readInt(String prompt, boolean nullable, int min, int max) {
        while (true) {
            String valueStr = readString(prompt, nullable);
            if (nullable && valueStr == null) {
                return Integer.MIN_VALUE; //  Возвращаем специальное значение, чтобы сигнализировать о null для nullable int
            }
            try {
                int value = Integer.parseInt(valueStr);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Значение должно быть в диапазоне от " + min + " до " + max + ". Повторите ввод.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите целое число.");
            }
        }
    }

    public Long readLong(String prompt, boolean nullable, Long min, Long max) {
        while (true) {
            String valueStr = readString(prompt, nullable);
            if (nullable && valueStr == null) {
                return null;
            }
            try {
                Long value = Long.parseLong(valueStr);
                if (min != null && value < min) {
                    System.out.println("Значение должно быть больше " + (min -1) + ". Повторите ввод.");
                    continue;
                }
                if (max != null && value > max) {
                    System.out.println("Значение должно быть не больше " + max + ". Повторите ввод.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите целое число.");
            }
        }
    }

    public Float readFloat(String prompt, boolean nullable, Float min, Float max) {
        while (true) {
            String valueStr = readString(prompt, nullable);
            if (nullable && valueStr == null) {
                return null;
            }
            try {
                Float value = Float.parseFloat(valueStr);
                if (min != null && value < min) {
                    System.out.println("Значение должно быть больше " + min + ". Повторите ввод.");
                    continue;
                }
                if (max != null && value > max) {
                    System.out.println("Значение должно быть не больше " + max + ". Повторите ввод.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите число с плавающей точкой.");
            }
        }
    }

    public <E extends Enum<E>> E readEnum(String prompt, Class<E> enumClass) {
        while (true) {
            System.out.print(prompt);
            String valueStr = scanner.nextLine().trim().toUpperCase();
            try {
                return Enum.valueOf(enumClass, valueStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректный ввод. Введите одно из значений: " + Arrays.toString(enumClass.getEnumConstants()));
            }
        }
    }

    public Coordinates readCoordinates() {
        Long x = readLong("Введите координату X (не может быть null, макс. 588): ", false, null, 588L);
        if (x == null) return null;

        int y = readInt("Введите координату Y (макс. 59): ", false, Integer.MIN_VALUE, 59);
        if (y == Integer.MIN_VALUE) return null;

        return new Coordinates(x, y);
    }

    public Human readHuman() {
        Long age = readLong("Введите возраст губернатора (должен быть > 0, может быть null, пустая строка для null): ", true, 1L, Long.MAX_VALUE);
        if (age == null && !isNullInput()) return null; // Если не null, то ошибка ввода

        if (isNullInput()) {
            return new Human(null); // Возвращаем Human с null age, если пользователь ввел пустую строку
        } else if (age != null) {
            return new Human(age);
        } else {
            return null; // Ошибка ввода возраста
        }
    }

    public City readCity() {
        String name = readString("Введите имя города (на английском; не может быть null и пустым): ", false);
        if (name == null) return null;

        Coordinates coordinates = readCoordinates();
        if (coordinates == null) return null;

        int area = readInt("Введите площадь города (должна быть > 0): ", false, 1, Integer.MAX_VALUE);
        if (area == Integer.MIN_VALUE) return null;

        Long population = readLong("Введите население города (должно быть > 0): ", false, 1L, Long.MAX_VALUE);
        if (population == null) return null;

        Float metersAboveSeaLevel = readFloat("Введите высоту над уровнем моря (может быть null, пустая строка для null): ", true, null, null);
        if (metersAboveSeaLevel == null && !isNullInput()) return null; // Если не null, то ошибка ввода

        int timezone = readInt("Введите часовой пояс (от -13 до 15): ", false, -13, 15);
        if (timezone == Integer.MIN_VALUE) return null;

        long carCode = readLong("Введите carCode (от 1 до 1000): ", false, 1L, 1000L);

        Climate climate = readEnum("Выберите климат (" + Arrays.toString(Climate.values()) + "): ", Climate.class);
        if (climate == null) return null;

        Human governor = readHuman();
        if (governor == null) return null;

        return new City(name, coordinates, area, population, metersAboveSeaLevel, timezone, carCode, climate, governor);
    }


    public boolean isNullInput() {
        return isNullInput;
    }

    private void setNullInput(boolean value) {
        isNullInput = value;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) { // <---- Добавьте setScanner() в InputReader
        this.scanner = scanner;
    }
}
