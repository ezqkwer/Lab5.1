package org.example.Commands;
import org.example.City;
import org.example.CityCollectionManager;
import org.example.InputReader;

/**
 * Команда update.
 */
public class UpdateCommand implements Command {
    private final InputReader inputReader;
    private final CityCollectionManager collectionManager;

    public UpdateCommand(InputReader inputReader, CityCollectionManager collectionManager) {
        this.inputReader = inputReader;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        if (argument == null) {
            System.out.println("Необходимо указать id города для обновления.");
            return;
        }
        try {
            Long id = Long.parseLong(argument);

            if (!collectionManager.exists(id)) { // Используем метод exists() из CityCollectionManager
                System.out.println("Город с id=" + id + " не найден.");
                return;
            }

            City updatedCity = inputReader.readCity(); // Используем InputReader для чтения города
            if (updatedCity != null) {
                if (collectionManager.updateCity(id, updatedCity)) {
                    System.out.println("Город с id=" + id + " успешно обновлен.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат id. Id должен быть числом.");
        }
    }
    @Override
    public String getDescription() {
        return "обновить элемент коллекции по id";
    }
}
