package org.example;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс для хранения истории команд.
 */
public class HistoryManager {
    private final Deque<String> commandHistory = new LinkedList<>();
    private static final int HISTORY_SIZE = 7;


    public void addCommand(String commandName) {
        if (commandName == null || commandName.trim().isEmpty()) return; // Не добавляем пустые команды

        if (commandHistory.size() >= HISTORY_SIZE) {
            commandHistory.pollFirst(); // Удаляем самую старую команду, если история заполнена
        }
        commandHistory.offerLast(commandName); // Добавляем новую команду в конец
    }


    public List<String> getCommandHistory() {   //вывод копии (инкапсуляция и беопасность)
        return new ArrayList<>(commandHistory);
    }
}
