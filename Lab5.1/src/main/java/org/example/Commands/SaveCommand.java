package org.example.Commands;
import org.example.CityCollectionManager;
import org.example.FileManager;
import java.io.IOException;


/**
 * Команда save.
 */
public class SaveCommand implements Command {
    private final FileManager fileManager;
    private final CityCollectionManager collectionManager;

    public SaveCommand(FileManager fileManager, CityCollectionManager collectionManager) {
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        try {
            collectionManager.saveCollectionToFile(fileManager);
            System.out.println("Коллекция сохранена в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения в файл: " + e.getMessage());
        }
    }
    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }
}
