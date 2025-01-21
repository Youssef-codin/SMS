import java.util.HashMap;

public abstract class School {

    private static int id = 1000;

    //list of student objects ID : StudentObj
    private static HashMap<Integer, Student> students = new HashMap<>();

    //placeholder student for testing


    //getter methods
    static void printList(){
        System.out.println(students);
    }
    static Student getStudentObj(int ID){
        return students.get(ID);
    }

    //setter methods
    static void addStudent(Student student){
        students.put(id, student);
        id++;
    }

    static void removeStudent(int id){
        students.remove(id);
    }
}
