import java.util.ArrayList;
import java.util.HashMap;

public class Student extends School{

    private static int numOfStudents = 0;

    private String name;
    private int age;
    private int GPA;
    private final HashMap<Subject, Integer> subjects = new HashMap<>();

    //Constructor Classes
    Student(String name, int age,
            ArrayList<Subject> subjects){
        this.name = name;
        this.age = age;
        this.GPA = 0;
        for(Subject subject : subjects) {
            this.subjects.put(subject, 0);
        }
        numOfStudents++;
        addStudent(this);
    }

    @Override
    public String toString(){
        return "name: " + this.name + " " + "Age: " + this.age + " "  + "GPA: " +
                this.GPA;
    }

    //getter methods
    String getName(){
        return this.name;
    }

    int getAge(){
        return this.age;
    }

    int getGPA(){
        return this.GPA;
    }

    HashMap<Subject, Integer> getSubjectsAndGrades(){
        return this.subjects;
    }

    static int getNumOfStudents(){
        return numOfStudents;
    }


    //setter methods
    void setName(String name){
        this.name = name;
    }

    void setAge(int age){
        this.age = age;
    }

    void setGPA(int GPA){
        this.GPA = GPA;
    }

    void addSubject(Subject subject){
        this.subjects.put(subject, 0);
    }
    void setGrade(Subject subject, int newGrade){
        subjects.put(subject, newGrade);
    }

    void removeSubject(Subject subject){
        this.subjects.remove(subjects.remove(subject));
    }
}
