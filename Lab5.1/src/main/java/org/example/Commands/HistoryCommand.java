package org.example.Commands;
import org.example.CityCollectionManager;

import java.util.List;


/**
 * Команда history.
 */
public class HistoryCommand implements Command {
    private final CityCollectionManager collectionManager;

    public HistoryCommand(CityCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        List<String> history = collectionManager.getHistoryManager().getCommandHistory();
        if (history.isEmpty()) {
            System.out.println("История команд пуста.");
        } else {
            System.out.println("Последние 7 команд:");
            for (String command : history) {
                System.out.println(command);
            }
        }
    }
    @Override
    public String getDescription() {
        return "вывести последние 7 команд";
    }
}
