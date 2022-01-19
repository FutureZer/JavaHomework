package futuzer.parser;

import futuzer.downloader.DownloadThread;
import futuzer.manager.Manager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommandParser {

    public static Manager pool = new Manager();

    public static void loadParse(String line) {
        String[] urls = line.split(" ");
        for (int i = 1; i < urls.length; ++i) {
            pool.doDownload(new DownloadThread(urls[i]));
        }
    }

    public static void setDirectory(String line) {
        String[] directories = line.split(" ");
        if (directories.length != 2) {
            System.out.println("Команда /dest принимает только 1 аргумент!");
            return;
        }
        Path path = Paths.get(directories[1]);
        if (Files.exists(path)) {
            DownloadThread.setDirectory(path.toString());
            System.out.println("Путь установлен успешно!");
        } else {
            System.out.println("Указан некорректный путь");
            return;
        }
    }
}
