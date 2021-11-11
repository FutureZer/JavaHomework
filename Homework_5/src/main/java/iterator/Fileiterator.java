package iterator;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Fileiterator implements Iterator<String>, AutoCloseable {

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
            if (!hasNext()) {
                throw new NoSuchElementException("Итератор дошел до конца файла");
            }
            String result = currentLine;
            currentLine = reader.readLine();
            return result;
        } catch (NoSuchElementException ex) {
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
