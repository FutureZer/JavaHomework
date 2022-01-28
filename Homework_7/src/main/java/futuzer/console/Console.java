package futuzer.console;

import futuzer.utils.DayUtils;
import futuzer.utils.QueueUtils;
import futuzer.work_process.Day;

import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Console {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        Day process = new Day(queue);
        possibleActions();
        int command;
        do {
            command = getCommand();
            switch (command) {
                case 1 -> QueueUtils.printAllElements(queue);
                case 2 -> QueueUtils.addThreeElemsIfEmpty(queue);
                case 3 -> DayUtils.setBorders(process, scan, true);
                case 4 -> DayUtils.setBorders(process, scan, false);
                case 5 -> process.setWorkingTime(DayUtils.getMilliseconds(scan));
                case 6 -> possibleActions();
                case 7 -> process.startDay();
            }
        } while (command != 8);
    }

    /**
     * Вывести список всех возможных команд в консоль
     */
    public static void possibleActions() {
        System.out.println("1. Вывести все элементы очереди");
        System.out.println("2. Самостоятельно добавить несколько случайных элементов в очередь, если она пустая");
        System.out.println("3. Изменить правую и левую границы времени на создание числа (по умолчанию 2500 и 5000)");
        System.out.println("4. Изменить правую и левую границы времени на потребление числа (по умолчанию 2500 и 5000)");
        System.out.println("5. Изменить кол-во миллисекунд для завершения дня");
        System.out.println("6. Показать все возможные действия");
        System.out.println("7. Запустить день");
        System.out.println("8. Выйти из программы");
    }

    /**
     * Получить команду от пользователя
     * @return номер действия
     */
    public static int getCommand() {
        int command = 0;
        while (command < 1 || command > 8) {
            System.out.print("Введите номер действия: ");
            String action = scan.next();
            try {
                command = Integer.parseInt(action);
            } catch (NumberFormatException ex) {
                System.out.println("Неверный формат ввода (нужно ввести целое число)");
                continue;
            }
            if (command < 1 || command > 8) {
                System.out.println("Число должно быть в промежутке от 1 до 7 включительно");
            }
        }
        return command;
    }
}
