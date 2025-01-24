import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SubjectDeserializer implements JsonDeserializer<Subject> {
    @Override
    public Subject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
            int marks = json.getAsInt();
            System.out.println("Warning: Expected a JSON object for Subject, but found a primitive value: " + marks);
            return new Subject("Unknown", marks); // Use Subject for primitive values
        }

        if (!json.isJsonObject()) {
            throw new JsonParseException("Expected a JSON object for Subject, but found: " + json);
        }

        JsonObject jsonObject = json.getAsJsonObject();
        System.out.println("Deserializing subject JSON: " + jsonObject);

        String name = jsonObject.get("name").getAsString();
        int marks = jsonObject.get("marks").getAsInt();

        // Check if the subject already exists and return the existing subject
        Subject existingSubject = Subject.getSubjectByName(name);
        if (existingSubject != null) {
            return existingSubject;
        }

        return new Subject(name, marks);
    }
}
