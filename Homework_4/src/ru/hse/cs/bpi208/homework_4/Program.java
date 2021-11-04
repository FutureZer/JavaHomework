package ru.hse.cs.bpi208.homework_4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Создание итератора
        System.out.println("Введите путь к текстовому файлу:");
        String path = input.next();
        Optional<Fileiterator> iterator = getIterator(path);
        if (iterator.isEmpty()) {
            iterator = getIterator("src/ru/hse/cs/bpi208/homework_4/text.txt");
        }

        // Работа с итератором
        boolean toContinue = true;
        displayCommands();
        do {
            System.out.println("Выберете номер действия");
            String value = input.next();
            switch (value) {
                case "1" -> {
                    if (iterator.get().hasNext()) {
                        System.out.println(iterator.get().next());
                    } else {
                        System.out.println("Строки в файле закончились");
                        toContinue = false;
                    }
                }
                case "2" -> {
                    while (iterator.get().hasNext()) {
                        System.out.println(iterator.get().next());
                    }
                    toContinue = false;
                }
                case "3" -> toContinue = false;
                default -> System.out.println("Выбран неверный номер действия");
            }
        } while (toContinue);
        try {
            iterator.get().close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Возникла проблема при закрытии потока");
        }

        // Возможность повторной работы программы
        System.out.println("Хотите ли проитерироваться по новому файлу?");
        System.out.println("Для продолжения работы программы введите \"+\"");
        if (input.next().equals("+")) {
            main(args);
        }
    }

    /**
     * Инициализация итератора
     *
     * @param path путь к файлу, по которому будет происходить итерация
     * @return Интератор или null
     */
    public static Optional<Fileiterator> getIterator(String path) {
        Optional<Fileiterator> iterator = Optional.empty();
        try {
            iterator = Optional.of(
                    new Fileiterator(path));
        } catch (FileNotFoundException ex) {
            System.out.println("Неверный путь файла, будет использован файл проекта");
        } catch (IOException ex) {
            System.out.println("Возникла проблема при чтении файла, будет использован файл проекта");
        }
        return iterator;
    }

    /**
     * Вывод возможных действий при работе с итератором
     */
    public static void displayCommands() {
        System.out.println("1. Прочесть текущую строку из файла");
        System.out.println("2. Считать весь файл");
        System.out.println("3. Выйти из программы или перейти на использование другово файла");
    }
}

