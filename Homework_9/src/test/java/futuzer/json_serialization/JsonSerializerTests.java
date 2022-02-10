package futuzer.json_serialization;

import futuzer.person.NullPerson;
import futuzer.person.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JsonSerializerTests {

    @Test
    public void jsonSerializedPersonEqualToItsTrueJsonRepresentation() {
        Person example = new Person("Иванов", "Иван", "19", "90");
        JsonSerializer serializer = new JsonSerializer();
        try {
            assertEquals(serializer.toJson(example), example.getPersonHandedJson());
        } catch (JsonSerializationException ex) {
            System.out.println(ex.getMessage());
            assert false;
        }
    }

    @Test
    public void jsonSerializedPersonWithNullFieldEqualToItsTrueJsonRepresentation() {
        Person example = new Person("Иванов", "Иван", null, "90");
        JsonSerializer serializer = new JsonSerializer();
        try {
            assertEquals(serializer.toJson(example), example.getPersonHandedJson());
        } catch (JsonSerializationException ex) {
            System.out.println(ex.getMessage());
            assert false;
        }
    }

    @Test
    public void jsonSerializedNullPersonWithNullFieldEqualToItsTrueJsonRepresentation() {
        NullPerson example = new NullPerson("Иванов", "Иван", null, "90");
        JsonSerializer serializer = new JsonSerializer();
        try {
            assertEquals(serializer.toJson(example), example.getPersonHandedJson());
        } catch (JsonSerializationException ex) {
            System.out.println(ex.getMessage());
            assert false;
        }
    }
}
