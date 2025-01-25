package mainFiles;

import java.util.HashMap;
import java.util.Map;

public abstract class School {

    private static String ID = "1000";

    //Hashmap of student objects ID : StudentObj
    private static HashMap<String, Student> students = new HashMap<>();

    //getter methods
    public static void getList(){
        for(Map.Entry<String, Student> entry : students.entrySet()){
            String ID = entry.getKey();
            Student student = entry.getValue();
            System.out.println(ID + " | " + student);
        }
    }

    public static Student getStudentObj(String ID){
        return students.get(ID);
    }

    public static HashMap<String, Student> getStudents(){
        return students;
    }

    public static String getID(){
        return ID;
    }

    //setter methods
    public static void addStudent(Student student, boolean AddID){
        students.put(ID, student);
        if(AddID) {
            int intID = Integer.parseInt(ID) + 1;
            ID = Integer.toString(intID);
        }
    }

    public static void removeStudent(String id){
        students.remove(id);
        Student.minusNumOfStudents();
    }

    public static void setStudents(HashMap<String, Student> loaded_students){
        students = loaded_students;
    }

    public static void setID(String latestID){
        School.ID = latestID;
    }
}
