package org.example.Commands;
import org.example.CityCollectionManager;

/**
 * Команда info.
 */
public class InfoCommand implements Command {
    private final CityCollectionManager collectionManager;

    public InfoCommand(CityCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        System.out.println(collectionManager.getCollectionInfo()); // Вывод информации через CityCollectionManager
    }
    @Override
    public String getDescription() {
        return "вывести информацию о коллекции";
    }
}
