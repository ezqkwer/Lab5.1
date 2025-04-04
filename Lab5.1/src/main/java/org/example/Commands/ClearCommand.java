package org.example.Commands;
import org.example.CityCollectionManager;


/**
 * Команда clear.
 */
public class ClearCommand implements Command {
    private final CityCollectionManager collectionManager;

    public ClearCommand(CityCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        collectionManager.clearCollection();
        System.out.println("Коллекция очищена.");
    }
    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }
}
