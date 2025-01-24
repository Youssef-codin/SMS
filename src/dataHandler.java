import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class dataHandler {
    private static final String DATA_JSON = "dat files/data.json";
    private static final String SCHOOL_JSON = "dat files/school.json";
    private static final String SUBJECTS_JSON = "dat files/subjects.json";

    static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Student.class, new StudentSerializer())
            .registerTypeAdapter(Subject.class, new SubjectSerializer()) // Register the SubjectSerializer
            .registerTypeAdapter(Subject.class, new SubjectDeserializer()) // Register the SubjectDeserializer
            .registerTypeAdapter(Student.class, new StudentDeserializer())
            .registerTypeAdapter(School.class, new SchoolDeserializer())
            .create();

    // stores [0]int ID and [1]int numOfStudents
    public static void saveData() {
        try (FileWriter writer = new FileWriter(DATA_JSON)) {
            int[] data = {Integer.parseInt(School.getID()), Student.getNumOfStudents()};
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.out.println("Something went wrong in saveData.");
        }
    }

    // loads [0]int ID and [1]int numOfStudents
    public static void loadData() {
        try (Reader reader = new FileReader(DATA_JSON)) {
            List<Integer> data = gson.fromJson(reader, new TypeToken<List<Integer>>(){}.getType());
            School.setID(String.valueOf(data.get(0)));
            Student.setNumOfStudents(data.get(1));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong in loadData: " + e.getMessage());
        }
    }

    // School Data
    public static void saveSchool() {
        try (FileWriter writer = new FileWriter(SCHOOL_JSON)) {
            String json = gson.toJson(School.getStudents());

            // Debug statement to check serialized data
            System.out.println("Serialized school JSON: " + json);

            writer.write(json);
        } catch (IOException e) {
            System.out.println("Something went wrong in saveSchool.");
        }
    }

    public static void loadSchool() {
        try (FileReader reader = new FileReader(SCHOOL_JSON)) {
            Type type = new TypeToken<HashMap<String, Student>>(){}.getType();
            HashMap<String, Student> loadedStudents = gson.fromJson(reader, type);

            // Debug statement to print the loaded data
            System.out.println("Loaded students: " + loadedStudents);

            School.setStudents(loadedStudents);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong in loadSchool: " + e.getMessage());
        }
    }

    // Subject Data
    public static void saveSubjects() {
        try (FileWriter writer = new FileWriter(SUBJECTS_JSON)) {
            gson.toJson(Subject.getSubjects(), writer);
        } catch (IOException e) {
            System.out.println("Something went wrong in saveSubjects.");
        }
    }

    public static void loadSubjects() {
        ArrayList<Subject> loaded_subjects = new ArrayList<>();
        try (FileReader reader = new FileReader(SUBJECTS_JSON)) {
            loaded_subjects = gson.fromJson(reader, new TypeToken<ArrayList<Subject>>(){}.getType());

            // Debug statement to print the loaded data
            System.out.println("Loaded subjects: " + loaded_subjects);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong in loadSubjects: " + e.getMessage());
        }
        Subject.setAvailableSubjects(loaded_subjects);
    }

    public static void saveAndLoadALL() {
        saveData();
        saveSchool();
        saveSubjects();

        loadData();
        loadSchool();
        loadSubjects();

        // Debug statements to check the loaded data
        System.out.println("After loading data:");
        System.out.println("School ID: " + School.getID());
        System.out.println("Students: " + School.getStudents());
        System.out.println("Subjects: " + Subject.getSubjects());


    }
}
