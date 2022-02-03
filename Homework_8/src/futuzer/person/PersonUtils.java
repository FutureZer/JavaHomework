package futuzer.person;

import java.util.Comparator;
import java.util.List;

public class PersonUtils {

    public static void sort(List<Person> people) {
        people.sort(Comparator.comparing(Person::getLastname)
                .thenComparing(Person::getFirstname)
                .thenComparing(Person::getAge));
    }
}
