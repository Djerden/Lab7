package io;

/**
 * Console implementation of the Writer interface
 */
public class ConsoleWriter implements Writer{

    @Override
    public void write(String str) {
        System.out.println(str);
    }
}
