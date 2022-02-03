package futuzer.console;

import futuzer.generator.PersonGenerator;
import futuzer.person.Person;
import futuzer.person.PersonUtils;

import java.util.List;
import java.util.Scanner;

public class Console {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        PersonGenerator gen = new PersonGenerator();
        int number = -1;
        while (number < 1) {
            System.out.println("Введите количество людей для генерации");
            try {
                String temp = scan.next();
                number = Integer.parseInt(temp);
            } catch (NumberFormatException e) {
                System.out.println("Введите целое положительное число");
            }
        }
        List<Person> people = gen.generateInRange(number);
        System.out.println("================= Not Sorted =================");
        printAllPeople(people);
        PersonUtils.sort(people);
        System.out.println("================= Sorted =================");
        printAllPeople(people);
    }

    public static void printAllPeople(List<Person> people) {
        for (Person person : people) {
            System.out.println(person.getLastname() + " " +
                    person.getFirstname() + " " +
                    person.getAge());
        }
    }
}
