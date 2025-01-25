package Data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import mainFiles.*;
import java.lang.reflect.Type;

public class SubjectSerializer implements JsonSerializer<Subject> {
    @Override                    //Take subject
    public JsonElement serialize(Subject subject,
                                 Type type,
                                 JsonSerializationContext context) {
        //Make a json object
        JsonObject jsonObject = new JsonObject();
        //Get the properties of the subject then add them to the json object
        jsonObject.addProperty("SubjectName", subject.getSubjectName());
        jsonObject.addProperty("maxGrade", subject.getMaxGrade());
        //return the subject as jsonObject
        return jsonObject;
    }
}
