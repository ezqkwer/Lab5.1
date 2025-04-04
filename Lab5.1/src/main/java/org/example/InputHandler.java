package org.example;

import org.example.Commands.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Класс для обработки ввода пользователя и выполнения команд.
 */
public class InputHandler {
    private final CityCollectionManager collectionManager;
    private final FileManager fileManager;
    private final InputReader inputReader; // Используем InputReader
    public final Map<String, Command> commandRegistry;



    public InputHandler(CityCollectionManager collectionManager, FileManager fileManager, InputReader inputReader) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
        this.inputReader = inputReader; // Принимаем InputReader в конструкторе
        this.commandRegistry = initializeCommandRegistry();

    }

    private Map<String, Command> initializeCommandRegistry() {
        Map<String, Command> registry = new HashMap<>();
        registry.put("help", new HelpCommand(this));
        registry.put("info", new InfoCommand(collectionManager)); // Передаем CityCollectionManager
        registry.put("show", new ShowCommand(collectionManager)); // Передаем CityCollectionManager
        registry.put("add", new AddCommand(inputReader, collectionManager)); // Передаем зависимости
        registry.put("update", new UpdateCommand(inputReader, collectionManager));
        registry.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        registry.put("clear", new ClearCommand(collectionManager));
        registry.put("save", new SaveCommand(fileManager, collectionManager));
        registry.put("execute_script", new ExecuteScriptCommand(this));
        registry.put("exit", new ExitCommand(this)); // ExitCommand теперь класс
        registry.put("remove_greater", new RemoveGreaterCommand(inputReader, collectionManager));
        registry.put("remove_lower", new RemoveLowerCommand(inputReader, collectionManager));
        registry.put("history", new HistoryCommand(collectionManager)); // HistoryCommand теперь класс
        registry.put("sum_of_car_code", new SumOfCarCodeCommand(collectionManager)); // SumOfCarCodeCommand теперь класс
        registry.put("average_of_timezone", new AverageOfTimezoneCommand(collectionManager)); // AverageOfTimezoneCommand теперь класс
        registry.put("min_by_area", new MinByAreaCommand(collectionManager)); // MinByAreaCommand теперь класс
        return registry;
    }


    public void runInteractiveMode() {
        String commandLine;
        do {
            System.out.print("> ");
            commandLine = inputReader.getScanner().nextLine().trim(); // Используем Scanner из InputReader
            String[] parts = commandLine.split("\\s+", 2);
            String commandName = parts[0].toLowerCase();
            String argument = parts.length > 1 ? parts[1] : null;



            Command command = commandRegistry.get(commandName);
            if (command != null) {
                collectionManager.getHistoryManager().addCommand(commandName);
                command.execute(argument);
            } else {
                System.out.println("Неизвестная команда. Введите 'help' для справки.");
            }
        } while (!commandLine.equalsIgnoreCase("exit"));
    }


    public CityCollectionManager getCollectionManager() {
        return collectionManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }



    public InputReader getInputReader() {
        return inputReader;
    }

    public Map<String, Command> getCommandRegistry() {
        return commandRegistry;
    }
}