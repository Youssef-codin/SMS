import java.util.ArrayList;

public class Student extends School{

    private static int numOfStudents = 0;

    private String name;
    private int age;
    private double GPA;
    private ArrayList<Subject> subjects;

    //Constructor Class
    Student(String name, int age, double GPA,
            ArrayList<Subject> subjects){
        this.name = name;
        this.age = age;
        this.GPA = GPA;
        this.subjects = subjects;
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

    double getGPA(){
        return this.GPA;
    }

    void getSubjects(){
        System.out.println(this.subjects);
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

    void setGPA(double GPA){
        this.GPA = GPA;
    }

    void addSubject(Subject subject){
        subjects.add(subject);
    }

    void removeSubject(Subject subject){
        subjects.remove(subject);
    }
}
