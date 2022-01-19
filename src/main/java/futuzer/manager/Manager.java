package futuzer.manager;

import futuzer.downloader.DownloadThread;

import java.util.List;
import java.util.concurrent.*;

public class Manager {

    List<DownloadThread> threads = new CopyOnWriteArrayList<>();
    int cores = Runtime.getRuntime().availableProcessors();
    ExecutorService service = Executors.newFixedThreadPool(cores);

    public void doDownload(DownloadThread task) {
        threads.add(task);
        service.execute(task);
    }

    public void interruptAllDownload() {
        service.shutdown();
    }

}
