package commands;

import client.CommandFactory;
import exceptions.AbsenceArgumentException;
import exceptions.UnknownCommandException;
import io.CommandReader;
import io.ScriptReader;
import io.Writer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ScriptCommand implements SimpleArgCommand {

    private String fileName;

    private CommandFactory factory;
    private Writer writer;

    private Scanner scanner;

    public ScriptCommand(CommandFactory factory, Writer writer) {
        this.factory = factory;
        this.writer = writer;
    }

    @Override
    public void execute() {
        File file = new File(fileName);
        if (file.length() == 0) {
            throw new RuntimeException("Файл пуст");
        }

        try {
            scanner = new Scanner(file);
            CommandReader commandReader = new ScriptReader(scanner, factory, writer);
            String input;
            while (scanner.hasNextLine()) {
                try {
                    Command command = commandReader.readCommands();
                    command.execute();
                } catch (UnknownCommandException e) {
                    writer.write("Нечитаемая команда");
                } catch (NullPointerException e) {
                    writer.write("Ошибка ввода");
                } catch (AbsenceArgumentException e) {
                    writer.write("Одна из команд предполагает наличие аргумента");
                }


            }
        } catch (IOException e) {

        }
    }

    @Override
    public void setSimpleArg(String str) {
        fileName = str;
    }

    @Override
    public String toString() {
        return "execute_script";
    }

}
