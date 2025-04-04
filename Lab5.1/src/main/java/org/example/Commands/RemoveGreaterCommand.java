package org.example.Commands;
import org.example.City;
import org.example.CityCollectionManager;
import org.example.InputReader;


/**
 * Команда remove_greater.
 */
public class RemoveGreaterCommand implements Command {
    private final InputReader inputReader;
    private final CityCollectionManager collectionManager;

    public RemoveGreaterCommand(InputReader inputReader, CityCollectionManager collectionManager) {
        this.inputReader = inputReader;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        City city = inputReader.readCity();
        if (city != null) {
            collectionManager.removeGreater(city);
            System.out.println("Удалены элементы, превышающие заданный.");
        }
    }
    @Override
    public String getDescription() {
        return "удалить элементы, превышающие заданный";
    }
}