package org.example.Commands;

import org.example.CityCollectionManager;


/**
 * Команда average_of_timezone.
 */
public class AverageOfTimezoneCommand implements Command {
    private final CityCollectionManager collectionManager;

    public AverageOfTimezoneCommand(CityCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        double average = collectionManager.averageOfTimezone();
        System.out.println("Среднее значение timezone для всех городов: " + average);
    }
    @Override
    public String getDescription() {
        return "вывести среднее timezone";
    }
}
