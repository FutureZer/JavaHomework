package futuzer.downloader;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadThread implements Runnable {

    // Введенный url
    String url;
    // Путь к папке, куда необходимо скачать файл
    static String path = "src/main/resources/";

    public DownloadThread(String url) {
        this.url = url;
    }

    public static void setDirectory(String path) {
        DownloadThread.path = path;
    }

    public static String getDirectory() {
        return path;
    }

    @Override
    public void run() {
        try (BufferedInputStream input = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream(path + url.substring(url.lastIndexOf('/')))) {
            byte[] read = new byte[1024];
            int count;
            while ((count = input.read(read, 0, 1024)) != -1) {
                out.write(read, 0, count);
                out.flush();
            }
        } catch (MalformedURLException e) {
            System.out.print("Введен некорректный URL ");
            System.out.println(url);
            return;
        } catch (FileNotFoundException e) {
            System.out.print("В url файла присутствуют некорректные символы для создания файла. URL: ");
            System.out.println(url);
            return;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("Произошла ошибка при работе с файлом. URL: ");
            System.out.println(url);
            return;
        }
        System.out.print("Загружено успешно ");
        System.out.println(url);
    }
}
