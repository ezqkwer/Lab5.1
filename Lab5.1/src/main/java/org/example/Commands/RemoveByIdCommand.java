package org.example.Commands;
import org.example.CityCollectionManager;

/**
 * Команда remove_by_id.
 */
public class RemoveByIdCommand implements Command {
    private final CityCollectionManager collectionManager;

    public RemoveByIdCommand(CityCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        if (argument == null) {
            System.out.println("Необходимо указать id города для удаления.");
            return;
        }
        try {
            Long id = Long.parseLong(argument);
            if (collectionManager.removeCityById(id)) {
                System.out.println("Город с id=" + id + " успешно удален.");
            } else {
                System.out.println("Город с id=" + id + " не найден.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат id. Id должен быть числом.");
        }
    }
    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по id";
    }
}
