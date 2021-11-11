package iterator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.NoSuchElementException;

public class FileiteratorTest {
    @Test
    void fileIteratorConstructTrowsIOIfIncorrectFilePath() {
         assertThrows(IOException.class, () -> new Fileiterator(""));
    }

    @Test
    void hasNextReturnFalseWhenFileIsEmpty() {
        String path = FileiteratorTest.class.getResource("/empty.txt").getPath();
        try {
            assertFalse((new Fileiterator(path)).hasNext());
        } catch (IOException ex) {
            assert false;
        }
    }

    @Test
    void takeNextWhenFileIsOver() {
        String path = FileiteratorTest.class.getResource("/filled.txt").getPath();
        try (Fileiterator fileiterator = new Fileiterator(path)) {
            for (;;) {
                fileiterator.next();
            }
        } catch (NoSuchElementException ex) {
            assert true;
        } catch (IOException ex) {
            assert false;
        }
    }

    @Test
    void takeNextWhenFileCorrupted() {
        String path = FileiteratorTest.class.getResource("/fill.txt").getPath();
        try (Fileiterator fileiterator = new Fileiterator(path)) {
            fileiterator.close();
            assertThrows(UncheckedIOException.class, fileiterator::next);
        } catch (IOException ex) {
            assert false;
        }
    }

}
