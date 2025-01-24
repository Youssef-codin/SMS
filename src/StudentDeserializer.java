import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashMap;

public class StudentDeserializer implements JsonDeserializer<Student> {
    @Override
    public Student deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String name = jsonObject.get("name").getAsString();
        int age = jsonObject.get("age").getAsInt();
        int GPA = jsonObject.get("GPA").getAsInt();

        JsonObject subjectsJson = jsonObject.getAsJsonObject("subjects");
        HashMap<Subject, Integer> subjectsAndGrades = new HashMap<>();

        for (String key : subjectsJson.keySet()) {
            JsonObject subjectJson = subjectsJson.getAsJsonObject(key);
            String subjectName = subjectJson.get("name").getAsString();
            int marks = subjectJson.get("marks").getAsInt();
            Subject subject = Subject.getSubjectByName(subjectName);
            if (subject == null) {
                subject = new Subject(subjectName, 100); // Assuming max marks are 100
            }
            subjectsAndGrades.put(subject, marks);
        }

        Student student = new Student(name, age, subjectsAndGrades);
        student.setGPA(GPA);

        return student;
    }
}
