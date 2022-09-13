package commands;

import io.Writer;

public class HelpCommand implements Command{
    private String helpInformation = "    help : вывести справку по доступным командам\n" +
            "    info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
            "    show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
            "    insert null {element} : добавить новый элемент с заданным ключом\n" +
            "    update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
            "    remove_key null : удалить элемент из коллекции по его ключу\n" +
            "    clear : очистить коллекцию\n" +
            "    save : сохранить коллекцию в файл\n" +
            "    execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
            "    exit : завершить программу (без сохранения в файл)\n" +
            "    remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
            "    history : вывести последние 9 команд (без их аргументов)\n" +
            "    remove_greater_key null : удалить из коллекции все элементы, ключ которых превышает заданный\n" +
            "    remove_any_by_nationality nationality : удалить из коллекции один элемент, значение поля nationality которого эквивалентно заданному\n" +
            "    max_by_weight : вывести любой объект из коллекции, значение поля weight которого является максимальным\n" +
            "    filter_less_than_passport_i_d passportID : вывести элементы, значение поля passportID которых меньше заданного";

    private Writer writer;

    public HelpCommand(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void execute() {
        writer.write(helpInformation);
    }
}
