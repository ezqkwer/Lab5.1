package org.example.Commands;
import org.example.CityCollectionManager;

/**
 * Команда show.
 */
public class ShowCommand implements Command {
    private final CityCollectionManager collectionManager;

    public ShowCommand(CityCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        System.out.println(collectionManager.showCollection()); // Вывод коллекции через CityCollectionManager
    }
    @Override
    public String getDescription() {
        return "вывести все элементы коллекции";
    }
}
