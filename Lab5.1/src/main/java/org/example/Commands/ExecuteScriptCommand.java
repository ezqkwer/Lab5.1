package org.example.Commands;
import org.example.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Команда execute_script.
 */

public class ExecuteScriptCommand implements Command {
    private final InputHandler inputHandler; // Используем основной InputHandler
    private final CityCollectionManager collectionManager;
    private final FileManager fileManager;

    public ExecuteScriptCommand(InputHandler inputHandler) { // Принимаем InputHandler в конструкторе
        this.inputHandler = inputHandler; // Используем переданный InputHandler
        this.collectionManager = inputHandler.getCollectionManager();
        this.fileManager = inputHandler.getFileManager();
    }

    @Override
    public void execute(String argument) {
        if (argument == null) {
            System.out.println("Необходимо указать имя файла скрипта.");
            return;
        }
        File scriptFile = new File(argument);
        if (!scriptFile.exists() || !scriptFile.isFile()) {
            System.out.println("Файл скрипта не найден или не является файлом.");
            return;
        }

        Scanner originalScanner = null; // <---- Объявите Scanner originalScanner ЗДЕСЬ, ПЕРЕД блоком try

        try (Scanner scriptScanner = new Scanner(scriptFile)) {
            originalScanner = inputHandler.getInputReader().getScanner(); // <---- Присваиваем значение originalScanner ВНУТРИ блока try
            inputHandler.getInputReader().setScanner(scriptScanner);

            System.out.println("Выполнение скрипта из файла: " + argument);
            while (scriptScanner.hasNextLine()) {
                String commandLine = scriptScanner.nextLine().trim();
                if (commandLine.isEmpty() || commandLine.startsWith("#")) continue;
                System.out.println("> " + commandLine);
                String[] parts = commandLine.split("\\s+", 2);
                String commandName = parts[0].toLowerCase();
                String cmdArgument = parts.length > 1 ? parts[1] : null;

                collectionManager.getHistoryManager().addCommand(commandName);

                Command command = inputHandler.commandRegistry.get(commandName);
                if (command != null) {
                    if ("execute_script".equals(commandName)) {
                        System.out.println("Рекурсивный вызов execute_script запрещен.");
                    } else if ("exit".equals(commandName)) {
                        System.out.println("Команда exit в скрипте игнорируется.");
                    } else {
                        try {
                            command.execute(cmdArgument);
                        } catch (Exception e) {
                            System.err.println("Ошибка при выполнении команды '" + commandName + "' из скрипта: " + e.getMessage());
                        }
                    }
                } else {
                    System.out.println("Неизвестная команда в скрипте: " + commandName);
                }
            }
            System.out.println("Выполнение скрипта завершено.");

        } catch (FileNotFoundException e) {
            System.out.println("Файл скрипта не найден: " + argument);
        } finally {
            inputHandler.getInputReader().setScanner(originalScanner); // <---- Используем originalScanner В блоке finally (теперь он виден)
        }
    }


    @Override
    public String getDescription() {
        return "исполнить скрипт из файла";
    }
}
