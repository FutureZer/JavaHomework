import java.net.*;
import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerStream implements Runnable {

    private static final List<String> allNames = new CopyOnWriteArrayList<>();
    private final Socket socket;

    public ServerStream(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String name;
            do {
                name = br.readLine();
            } while (allNames.contains(name));

            System.out.println(name + " вошел в чат");
            allNames.add(name);

            while (true) {
                String line = br.readLine();
                if (line.equals("/exit")) {
                    System.out.println(name + " вышел из чата");
                    break;
                }
                System.out.println(name + ": " + line);
            }

        } catch (IOException ignored) {
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) { }
        }
    }
}
