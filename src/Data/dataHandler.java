package Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import mainFiles.*;

public abstract class dataHandler {
    private static final String DATA_JSON = "JSON files/data.json";
    private static final String SCHOOL_JSON = "JSON files/school.json";
    private static final String SUBJECTS_JSON = "JSON files/subjects.json";

    static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Student.class, new StudentSerializer())
            .registerTypeAdapter(Student.class, new StudentDeserializer())
            .registerTypeAdapter(Subject.class, new SubjectSerializer())
            .registerTypeAdapter(Subject.class, new SubjectDeserializer())
            .create();

    //Data
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
            int[] data = gson.fromJson(reader, int[].class);
            School.setID(String.valueOf(data[0]));
            Student.setNumOfStudents(data[1]);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong in loadData.");
        }
    }

    // School Data
    public static void saveSchool() {
        try (FileWriter writer = new FileWriter(SCHOOL_JSON)) {
            String json = gson.toJson(School.getStudents());
            writer.write(json);
        } catch (IOException e) {
            System.out.println("Something went wrong in saveSchool.");
        }
    }

    public static void loadSchool() {
        try(FileReader fileReader = new FileReader(SCHOOL_JSON)){
            Type hashMapType = new TypeToken<HashMap<String, Student>>(){}.getType();
            HashMap<String, Student> loadedStudents = gson.fromJson(fileReader, hashMapType);
            if (loadedStudents != null) {
                School.setStudents(loadedStudents);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong in loadSchool.");
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
        ArrayList<Subject> loaded_subjects;
        try (FileReader reader = new FileReader(SUBJECTS_JSON)) {
            loaded_subjects = gson.fromJson(reader, new TypeToken<ArrayList<Subject>>(){}.getType());
            Subject.setAvailableSubjects(loaded_subjects);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong in loadSubjects.");
        }
    }

    public static void saveAndLoadALL() {
        saveData();
        saveSchool();
        saveSubjects();

        loadData();
        loadSchool();
        loadSubjects();
    }
}