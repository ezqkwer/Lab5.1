package org.example.Commands;
import org.example.City;
import org.example.CityCollectionManager;
import org.example.InputReader;


/**
 * Команда remove_lower.
 */
public class RemoveLowerCommand implements Command {
    private final InputReader inputReader;
    private final CityCollectionManager collectionManager;

    public RemoveLowerCommand(InputReader inputReader, CityCollectionManager collectionManager) {
        this.inputReader = inputReader;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        City city = inputReader.readCity();
        if (city != null) {
            collectionManager.removeLower(city);
            System.out.println("Удалены элементы, меньшие, чем заданный.");
        }
    }
    @Override
    public String getDescription() {
        return "удалить элементы, меньшие, чем заданный";
    }
}
