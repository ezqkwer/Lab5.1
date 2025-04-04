package org.example.Commands;

import org.example.City;
import org.example.CityCollectionManager;
import org.example.InputReader;

/**
 * Команда add.
 */
public class AddCommand implements Command {
    private final InputReader inputReader;
    private final CityCollectionManager collectionManager;

    public AddCommand(InputReader inputReader, CityCollectionManager collectionManager) {
        this.inputReader = inputReader;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        City city = inputReader.readCity(); // Используем InputReader для чтения города
        if (city != null) {
            collectionManager.addCity(city);
            System.out.println("Город успешно добавлен.");
        }
    }
    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }
}