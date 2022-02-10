package futuzer.json_serialization;

import java.lang.reflect.Field;
import java.util.Objects;

public class JsonSerializer {

    public String toJson(Object object) throws JsonSerializationException {
        canSerialize(object);
        return makeJsonString(object);
    }

    private void canSerialize(Object object) throws JsonSerializationException {
        if (object == null) {
            throw new JsonSerializationException("Serialized object cannot be null");
        } else {
            Class<?> classPresent = object.getClass();
            if (!classPresent.isAnnotationPresent(JsonSerializable.class)) {
                throw new JsonSerializationException("Serialized object does not marked " +
                        "with JsonSerializable annotation");
            }
        }
    }

    private String makeJsonString(Object object) throws JsonSerializationException {
        StringBuilder converted = new StringBuilder();
        converted.append("{\n");
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonElement.class)) {
                try {
                    field.setAccessible(true);
                    Object fieldValue = field.get(object);
                    if (fieldValue == null && object.getClass().isAnnotationPresent(IgnoreNull.class)) {
                        continue;
                    }
                    String fieldName = !Objects.equals(field.getAnnotation(JsonElement.class).name(), "")
                            ? field.getAnnotation(JsonElement.class).name()
                            : field.getName();
                    converted.append("\"").append(fieldName).append("\": ");
                    converted.append("\"").append(field.get(object)).append("\",\n");
                    field.setAccessible(false);
                } catch (IllegalAccessException ex) {
                    throw new JsonSerializationException("Возникла ошибка при чтении данных полей объекта");
                }
            }
        }
        converted.append("}");
        return converted.toString();
    }

}
