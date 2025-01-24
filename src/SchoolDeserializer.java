import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class SchoolDeserializer implements JsonDeserializer<School> {
    @Override
    public School deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Deserialize the ID
        String id = jsonObject.get("ID").getAsString();
        School.setID(id);

        // Deserialize the students HashMap
        JsonObject studentsJson = jsonObject.getAsJsonObject("students");
        HashMap<String, Student> students = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : studentsJson.entrySet()) {
            Student student = context.deserialize(entry.getValue(), Student.class);
            students.put(entry.getKey(), student);
        }
        School.setStudents(students);

        return null; // Since School is abstract
    }
}
