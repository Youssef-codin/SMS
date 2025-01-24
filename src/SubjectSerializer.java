import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class SubjectSerializer implements JsonSerializer<Subject> {
    @Override
    public JsonElement serialize(Subject subject, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", subject.getSubjectName());
        jsonObject.addProperty("marks", subject.getMarks());
        return jsonObject;
    }
}
