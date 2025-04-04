package org.example.Commands;
import org.example.City;
import org.example.CityCollectionManager;


/**
 * Команда min_by_area.
 */
public class MinByAreaCommand implements Command {
    private final CityCollectionManager collectionManager;

    public MinByAreaCommand(CityCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        City minAreaCity = collectionManager.minByArea();
        if (minAreaCity != null) {
            System.out.println("Город с минимальной площадью:\n" + minAreaCity);
        } else {
            System.out.println("Коллекция пуста.");
        }
    }
    @Override
    public String getDescription() {
        return "вывести город с минимальной area";
    }
}
