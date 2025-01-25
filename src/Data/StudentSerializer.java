package Data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import mainFiles.*;

public class StudentSerializer implements JsonSerializer<Student> {
    @Override
    public JsonElement serialize(Student student, Type typeOfSrc, JsonSerializationContext context) {
        //make JsonObject which is the student object
        JsonObject jsonObject = new JsonObject();
        //Add the student properties to the JsonObject
        jsonObject.addProperty("name", student.getName());
        jsonObject.addProperty("age", student.getAge());

        //Make a new json object for the HashMap inside of students
        // <Subject, Integer>
        JsonObject subjectsJson = new JsonObject();
        //for every subject in subjects of hashmap<Subject, Integer>
        for (Subject subject : student.getSubjectsAndGrades().keySet()) {
            //make a new jsonObject that uses the Subject serializer and get it as a JsonObject
            JsonObject subjectJson = context.serialize(subject, Subject.class).getAsJsonObject();
            //get grade of the student
            int grade = student.getSubjectsAndGrades().get(subject);
            //makes it so subjectJson has <Subject, grade>
            subjectJson.addProperty("grade", grade);
            subjectsJson.add(subject.getName(), subjectJson);
        }
        //remember, this is a hashmap/dict
        jsonObject.add("subjects", subjectsJson);

        return jsonObject;
    }
}
