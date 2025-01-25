package Data;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import mainFiles.*;

public class StudentDeserializer implements JsonDeserializer<Student> {
    @Override
    public Student deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        //get the jsonObject
        JsonObject jsonObject = json.getAsJsonObject();

        //get the basic properties
        String name = jsonObject.get("name").getAsString();
        int age = jsonObject.get("age").getAsInt();

        //gets Subjects like math, english
        // <Subject, student's grade>
        JsonObject subjectsJson = jsonObject.getAsJsonObject("subjects");

        //we need to turn the json format to a Subject format
        HashMap<Subject, Integer> subjectsAndGrades = new HashMap<>();

        //for key (String subject's name) : the subjects like math, english etc.
        for (String key : subjectsJson.keySet()) {
            //get the jsonObject using its key for example .getAsJsonObject("math")
            //would give me a json object for the math subject
            JsonObject subjectJson = subjectsJson.getAsJsonObject(key);
            //we then deserialize the JsonObject("math") into a Subject class like normal
            //using the SubjectDeserializer
            Subject subject = context.deserialize(subjectJson, Subject.class);
            //Get the grade the student got remember this is the map
            //<Subject{subject stuff}, grade>
            int grade = subjectJson.get("grade").getAsInt();
            //Combine the subject after it gets deserialized and the grade into
            //one and store them in the HashMap used to make a Student Class
            subjectsAndGrades.put(subject, grade);
        }
        //use all the properties to make a new student
        return new Student(name, age, subjectsAndGrades);
    }
}
