package org.example.Commands;
import org.example.CityCollectionManager;


/**
 * Команда sum_of_car_code.
 */
public class SumOfCarCodeCommand implements Command {
    private final CityCollectionManager collectionManager;

    public SumOfCarCodeCommand(CityCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        long sum = collectionManager.sumOfCarCode();
        System.out.println("Сумма значений carCode для всех городов: " + sum);
    }
    @Override
    public String getDescription() {
        return "вывести сумму carCode";
    }
}