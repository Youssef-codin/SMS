package Data;

import com.google.gson.*;
import java.lang.reflect.Type;
import mainFiles.*;

public class SubjectDeserializer implements JsonDeserializer<Subject> {
    @Override
    public Subject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        //get the jsonObject
        JsonObject jsonObject = json.getAsJsonObject();
        //Get the jsonObject properties
        String SubjectName = jsonObject.get("SubjectName").getAsString();
        int maxGrade = jsonObject.get("maxGrade").getAsInt();

        //Check if the subject already exists and return the existing subject
        Subject existingSubject = Subject.subjectAlrExists(SubjectName);
        if (existingSubject != null) {
            return existingSubject;
        }
        //makes a new subject if the subject doesn't exist
        return new Subject(SubjectName, maxGrade, false);
    }
}
