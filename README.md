Реализовать консольное приложение, которое реализует управление коллекцией объектов в интерактивном режиме. В коллекции необходимо хранить объекты класса City, описание которого приведено ниже.

Разработанная программа должна удовлетворять следующим требованиям:

Класс, коллекцией экземпляров которого управляет программа, должен реализовывать сортировку по умолчанию.
Все требования к полям класса (указанные в виде комментариев) должны быть выполнены.
Для хранения необходимо использовать коллекцию типа java.util.LinkedHashSet
При запуске приложения коллекция должна автоматически заполняться значениями из файла.
Имя файла должно передаваться программе с помощью: аргумент командной строки.
Данные должны храниться в файле в формате csv
Чтение данных из файла необходимо реализовать с помощью класса java.io.BufferedInputStream
Запись данных в файл необходимо реализовать с помощью класса java.io.PrintWriter
Все классы в программе должны быть задокументированы в формате javadoc.
Программа должна корректно работать с неправильными данными (ошибки пользовательского ввода, отсутсвие прав доступа к файлу и т.п.).
В интерактивном режиме программа должна поддерживать выполнение следующих команд:

help : вывести справку по доступным командам

info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)

show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении

add {element} : добавить новый элемент в коллекцию

update id {element} : обновить значение элемента коллекции, id которого равен заданному

remove_by_id id : удалить элемент из коллекции по его id

clear : очистить коллекцию

save : сохранить коллекцию в файл

execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.

exit : завершить программу (без сохранения в файл)

remove_greater {element} : удалить из коллекции все элементы, превышающие заданный

remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный

history : вывести последние 7 команд (без их аргументов)

sum_of_car_code : вывести сумму значений поля carCode для всех элементов коллекции

average_of_timezone : вывести среднее значение поля timezone для всех элементов коллекции

min_by_area : вывести любой объект из коллекции, значение поля area которого является минимальным

Формат ввода команд:
Все аргументы команды, являющиеся стандартными типами данных (примитивные типы, классы-оболочки, String, классы для хранения дат), должны вводиться в той же строке, что и имя команды.
Все составные типы данных (объекты классов, хранящиеся в коллекции) должны вводиться по одному полю в строку.
При вводе составных типов данных пользователю должно показываться приглашение к вводу, содержащее имя поля (например, "Введите дату рождения:")
Если поле является enum'ом, то вводится имя одной из его констант (при этом список констант должен быть предварительно выведен).
При некорректном пользовательском вводе (введена строка, не являющаяся именем константы в enum'е; введена строка вместо числа; введённое число не входит в указанные границы и т.п.) должно быть показано сообщение об ошибке и предложено повторить ввод поля.
Для ввода значений null использовать пустую строку.
Поля с комментарием "Значение этого поля должно генерироваться автоматически" не должны вводиться пользователем вручную при добавлении.
Описание хранимых в коллекции классов:

public class City {

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    
    private String name; //Поле не может быть null, Строка не может быть пустой
    
    private Coordinates coordinates; //Поле не может быть null
    
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int area; //Значение поля должно быть больше 0
    private Long population; //Значение поля должно быть больше 0, Поле не может быть null
    private Float metersAboveSeaLevel;
    private int timezone; //Значение поля должно быть больше -13, Максимальное значение поля: 15
    private long carCode; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
    private Climate climate; //Поле не может быть null
    private Human governor; //Поле не может быть null
}
public class Coordinates {
    private Long x; //Максимальное значение поля: 588, Поле не может быть null
    private int y; //Максимальное значение поля: 59
}
public class Human {
    private Long age; //Значение поля должно быть больше 0
}
public enum Climate {
    RAIN_FOREST,
    OCEANIC,
    SUBARCTIC;
}
