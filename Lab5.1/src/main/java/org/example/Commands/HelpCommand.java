package org.example.Commands;

import org.example.InputHandler;
import java.util.Map;

/**
 * Команда help.
 */
public class HelpCommand implements Command {
    private final InputHandler inputHandler;

    public HelpCommand(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public void execute(String argument) {
        Map<String, Command> commandRegistry = inputHandler.getCommandRegistry(); // Получаем commandRegistry

        System.out.println("Доступные команды:"); // Добавляем заголовок

        for (Map.Entry<String, Command> entry : commandRegistry.entrySet()) { // Итерируемся по entrySet
            String commandName = entry.getKey(); // Получаем имя команды (ключ)
            Command command = entry.getValue(); // Получаем объект Command (значение)
            String description = command.getDescription(); // Получаем описание команды

            System.out.println(commandName + " : " + description); // Выводим имя и описание
        }
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }
}

