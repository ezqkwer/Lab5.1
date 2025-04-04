package org.example.Commands;
import org.example.InputHandler;

/**
 * Команда exit.
 */
public class ExitCommand implements Command {
    private final InputHandler inputHandler;

    public ExitCommand(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public void execute(String argument) {
        System.out.println("Завершение работы программы.");
        inputHandler.getInputReader().getScanner().close(); // Закрываем сканер при выходе
    }
    @Override
    public String getDescription() {
        return "завершить программу";
    }
}
