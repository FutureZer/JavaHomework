package futuzer.utils;

import futuzer.work_process.Day;

import java.util.Scanner;

public class DayUtils {

    /**
     * Обновление границы времени, затрачиваемого на создание или поедание числа
     * @param day класс, у которого обновляем
     * @param scan откуда ввод
     * @param isProducer флаг отвечающий за обновление у Producer или у Consumer
     */
    public static void setBorders(Day day, Scanner scan, boolean isProducer) {
        int left = 0;
        int right = 0;
        while (left >= right) {

            // Левая граница
            System.out.print("Введите левую границу: ");
            String action = scan.next();
            try {
                left = Integer.parseInt(action);
            } catch (NumberFormatException ex) {
                System.out.println("Неверный формат ввода (нужно ввести целое число)");
                continue;
            }

            // Правая граница
            System.out.print("Введите правую границу: ");
            action = scan.next();
            try {
                right = Integer.parseInt(action);
            } catch (NumberFormatException ex) {
                System.out.println("Неверный формат ввода (нужно ввести целое число)");
                continue;
            }

            // Условие выхода
            if (left >= right) {
                System.out.println("Правая граница должна быть строго больше");
            }
        }

        // По флагу определяем у кого обновляем время
        if (isProducer) {
            day.setProducerBorder(left, right);
        } else {
            day.setConsumerBorder(left, right);
        }
    }

    public static long getMilliseconds(Scanner scan) {
        long newTime = 0;
        while (newTime < 10000 || newTime > 200000) {
            // Левая граница
            System.out.print("Введите новую продолжительность дня: ");
            String action = scan.next();
            try {
                newTime = Long.parseLong(action);
            } catch (NumberFormatException ex) {
                System.out.println("Неверный формат ввода (нужно ввести целое число)");
                continue;
            }

            // Условие выхода
            if (newTime < 10000 || newTime > 200000) {
                System.out.println("число должно находиться между 10000 и 200000");
            }
        }
        return newTime;
    }
}
