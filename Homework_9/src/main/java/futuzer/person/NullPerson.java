package futuzer.person;

import futuzer.json_serialization.IgnoreNull;
import futuzer.json_serialization.JsonElement;
import futuzer.json_serialization.JsonSerializable;


@JsonSerializable
public class NullPerson {

    @JsonElement
    private String firstName;

    @JsonElement(name = "name")
    private String lastName;

    @JsonElement()
    private String age;

    private String weight;

    public NullPerson(String firstName, String lastName, String age, String weight) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
    }


    /**
     * Метод для тестирования, который собирает Json по известным параметрам для данного класса
     * @return строку формата Json
     */
    public String getPersonHandedJson() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("\"").append("firstName").append("\": \"").append(firstName).append("\",\n");
        builder.append("\"").append("name").append("\": \"").append(lastName).append("\",\n");
        builder.append("\"").append("age").append("\": \"").append(age).append("\",\n");
        builder.append("}");
        return builder.toString();
    }
}

