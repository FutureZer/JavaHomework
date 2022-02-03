package futuzer.generator;

import futuzer.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonGenerator {

    Random random = new Random();

    public String[] lastnames = {
            "Смирнов", "Иванов", "Кузнецов",
            "Соколов", "Попов", "Петров",
            "Лебедев", "Козлов", "Новиков",
            "Морозов", "Волков", "Соловьёв",
            "Васильев", "Зайцев", "Павлов"
    };

    public String[] maleNames = {
            "Александр", "Дмитрий", "Максим",
            "Даниил", "Кирилл", "Ярослав",
            "Денис", "Никита", "Иван",
            "Артём", "Тимофей", "Богдан",
            "Глеб", "Захар", "Матвей"
    };

    public String[] femaleNames = {
            "Анна", "Алина", "Дарья",
            "Екатерина", "Софья", "Анастасия",
            "Елена", "Елизавета", "Ирина",
            "Мария", "Амира", "Маргарита",
            "Наталья", "Юлия", "Ольга"
    };

    /**
     * Генерирует случайный объект типа Person
     * @return объект Person
     */
    public Person generate() {
        int sex = random.nextInt(2);
        String lastname, firstname;
        byte age;
        if (sex == 1) {
            lastname = lastnames[random.nextInt(lastnames.length)];
            firstname = maleNames[random.nextInt(maleNames.length)];
        } else {
            lastname = lastnames[random.nextInt(lastnames.length)] + "a";
            firstname = femaleNames[random.nextInt(femaleNames.length)];
        }
        age = (byte)(random.nextInt(30) + 10);
        return new Person(lastname, firstname, age);
    }

    /**
     * Генерирует n объектов класса Person
     * @param n Кол-во объектов класса Person
     * @return Список объектов типа Person
     */
    public List<Person> generateInRange(int n) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            people.add(generate());
        }
        return people;
    }
}
