package org.example;

import java.io.IOException;
import java.util.Scanner;

/**
 * Главный класс приложения, управляющий коллекцией городов.
 */
public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Использование: java Main <имя_csv_файла>");
            return;
        }

        String filePath = args[0];
        CityCollectionManager collectionManager = new CityCollectionManager();
        FileManager fileManager = new FileManager(filePath);
        InputReader inputReader = new InputReader(new Scanner(System.in));
        InputHandler inputHandler = new InputHandler(collectionManager, fileManager, inputReader);


        try {
            collectionManager.loadCollectionFromFile(fileManager);
            System.out.println("Коллекция успешно загружена из файла: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки коллекции из файла: " + e.getMessage());
            System.out.println("Начнем работу с пустой коллекцией.");
        }

        System.out.println("Консольное приложение управления коллекцией городов запущено.");
        inputHandler.runInteractiveMode();
    }
}