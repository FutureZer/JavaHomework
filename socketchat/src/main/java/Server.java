import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final int PORT = 48000;

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket (PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            ServerStream userStream = new ServerStream(socket);
            new Thread(userStream).start();
        }
    }

}
