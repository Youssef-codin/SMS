import java.util.ArrayList;
import java.util.HashMap;

public class Student {

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
        School.addStudent(this);
    }

    @Override
    public String toString(){
        return "name: " + this.name + " " + "Age: " + this.age + " "  + "GPA: " +
                this.GPA;
    }

    //getter methods
    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public int getGPA(){
        return this.GPA;
    }

    HashMap<Subject, Integer> getSubjectsAndGrades(){
        return this.subjects;
    }

    public static int getNumOfStudents(){
        return numOfStudents;
    }


    //setter methods
    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setGPA(int GPA){
        this.GPA = GPA;
    }

    public static void minusNumOfStudents(){
        numOfStudents--;
    }

    public void addSubject(Subject subject){
        this.subjects.put(subject, 0);
    }

    public void removeSubject(Subject subject){
        this.subjects.remove(subjects.remove(subject));
    }

    public void setGrade(Subject subject, int newGrade){
        subjects.put(subject, newGrade);
    }

}
