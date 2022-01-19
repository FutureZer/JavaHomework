package futuzer.parser;

import futuzer.downloader.DownloadThread;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTests {

    @Test
    void directoryRemainsSameWhenDestCommandContainsNumberOfArgumentsNotEqualToTwo() {
        String defaultDir = DownloadThread.getDirectory();
        CommandParser.setDirectory("/dest src/main/resources/ src/main/resources/");
        assertEquals(defaultDir, DownloadThread.getDirectory());
    }

    @Test
    void directoryRemainsSameWhenDestSecondArgumentIsNotAPath() {
        String defaultDir = DownloadThread.getDirectory();
        CommandParser.setDirectory("/dest incorrect/directory");
        assertEquals(defaultDir, DownloadThread.getDirectory());
    }

    @Test
    void directoryShouldChangeWhenSecondArgumentIsCorrect() {
        String defaultDir = DownloadThread.getDirectory();
        CommandParser.setDirectory("/dest src/test/resources/");
        assertNotEquals(defaultDir, DownloadThread.getDirectory());
    }
}
