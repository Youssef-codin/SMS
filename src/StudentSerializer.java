import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class StudentSerializer implements JsonSerializer<Student> {
    @Override
    public JsonElement serialize(Student student, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", student.getName());
        jsonObject.addProperty("age", student.getAge());
        jsonObject.addProperty("GPA", student.getGPA());

        JsonObject subjectsJson = new JsonObject();
        for (Subject subject : student.getSubjectsAndGrades().keySet()) {
            JsonObject subjectJson = new JsonObject();
            subjectJson.addProperty("name", subject.getName());
            subjectJson.addProperty("marks", student.getSubjectsAndGrades().get(subject)); // Corrected to use the student's grade
            subjectsJson.add(subject.getName(), subjectJson);
        }
        jsonObject.add("subjects", subjectsJson);

        return jsonObject;
    }
}
