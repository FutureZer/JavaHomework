package ru.hse.cs.bpi208.homework_4;

import java.io.*;
import java.util.Iterator;

public class Fileiterator implements Iterator {

    private String currentLine;
    private final BufferedReader reader;

    public Fileiterator(String path) throws IOException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        reader = new BufferedReader(fr);
        currentLine = reader.readLine();
    }

    @Override
    public boolean hasNext() {
        return currentLine != null;
    }

    @Override
    public String next() {
        try {
            String result = currentLine;
            currentLine = reader.readLine();
            return result;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
