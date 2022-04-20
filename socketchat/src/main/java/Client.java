import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {


    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(InetAddress.getLocalHost(), Server.PORT);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            System.out.println("В первой строке введите свое имя, а затем пишите сообщения");

            while (true) {
                String line = br.readLine();
                bw.write(line);
                bw.newLine();
                bw.flush();
                if (line.equals("/exit"))
                    break;
            }
        }
        socket.close();
    }
}
