package client_commands;

import collection.PersonCollection;
import commands.Command;
import io.Writer;

/**
 * Help command
 */
public class HelpCommand implements Command {
    private String helpInformation = "    help : output help for available commands\n" +
            "    info : output information about the collection (type, initialization date, number of items, etc.) to the standard output stream.\n" +
            "    show : output to the standard output stream all the elements of the collection in a string representation\n" +
            "    insert {number} : add a new person with a given number\n" +
            "    update {id} : update the value of a collection item whose id is equal to the specified one\n" +
            "    remove_key {number} : remove a person from the collection by his number\n" +
            "    clear : clear the collection\n" +
            "    execute_script file_name : read and execute the script from the specified file. The script contains commands in the same form in which they are entered by the user in interactive mode\n" +
            "    exit : end the program\n" +
            "    remove_greater : remove all items from the collection that exceed the specified\n" +
            "    history : output the last 9 commands\n" +
            "    remove_greater_key {number} : remove from the collection all items whose key exceeds the specified one\n" +
            "    remove_any_by_nationality : delete one element from the collection whose value of the nationality field is equivalent to the specified one\n" +
            "    max_by_weight : output any object from the collection whose weight field value is the maximum\n" +
            "    filter_less_than_passport_id {passportId}: output elements whose passport ID field value is less than the specified one";

    private Writer writer;

    public HelpCommand(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void execute() {
        writer.write(helpInformation);
    }

    @Override
    public void setCollection(PersonCollection personCollection) {

    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public String toString() {
        return "help";
    }
}
