import java.util.HashMap;
import java.util.Map;

public abstract class School {

    private static int id = 1000;

    //list of student objects ID : StudentObj
    private final static HashMap<Integer, Student> students = new HashMap<>();

    //getter methods
    public static void getList(){
        for(Map.Entry<Integer, Student> entry : students.entrySet()){
            Integer ID = entry.getKey();
            Student student = entry.getValue();
            System.out.println(ID + " | " + student);
        }
    }
    public static Student getStudentObj(int ID){
        return students.get(ID);
    }

    //setter methods
    public static void addStudent(Student student){
        students.put(id, student);
        id++;
    }

    public static void removeStudent(int id){
        students.remove(id);
        Student.minusNumOfStudents();
    }
}
