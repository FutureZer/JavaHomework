package futuzer.console;

import futuzer.parser.CommandParser;

import java.util.Objects;
import java.util.Scanner;

public class Console {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в загрузчик файлов. Введите \"/help\" для просмотра команд");
        String input = in.nextLine();
        while (!Objects.equals(input, "/exit")) {
            if (input.startsWith("/load")) {
                CommandParser.loadParse(input);
            } else if (input.startsWith("/dest")) {
                CommandParser.setDirectory(input);
            } else if (Objects.equals(input, "/help")) {
                commandList();
            } else {
                System.out.println("Type \"/help\" to see possible commands");
            }
            input = in.nextLine();
        }
        CommandParser.pool.interruptAllDownload();
    }

    public static void commandList() {
        System.out.println("/dest <path> - установить путь, в который будут скачиваться файлы");
        System.out.println("/load <url_1> <url_2> ... <url_n> - скачать n файлов");
        System.out.println("/exit - выход из программы");
        System.out.println("/help - получить список команд");
    }
}
