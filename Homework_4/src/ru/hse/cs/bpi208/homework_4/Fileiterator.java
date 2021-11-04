package ru.hse.cs.bpi208.homework_4;

import java.io.*;
import java.util.Iterator;

public class Fileiterator implements Iterator<String>, Closeable {

    private String currentLine;
    private final BufferedReader reader;
    private final FileReader fr;
    private final File file;

    public Fileiterator(String path) throws IOException {
        file = new File(path);
        fr = new FileReader(file);
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
            if (hasNext()) {
                throw new NullPointerException("Итератор дошел до конца файла");
            }
            String result = currentLine;
            currentLine = reader.readLine();
            return result;
        } catch (NullPointerException ex) {
            throw ex;
        } catch (IOException ex) {
            throw new UncheckedIOException("Возникла неизвестная проблема при работе с файлом", ex);
        }
    }

    @Override
    public void close() throws IOException {
        file.delete();
        fr.close();
        reader.close();
    }
}
