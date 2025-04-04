package org.example.Commands;


/**
 * Интерфейс для команд.
 */
public interface Command {
    void execute(String argument);
    String getDescription();
}