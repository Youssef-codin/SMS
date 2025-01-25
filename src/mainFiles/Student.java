package mainFiles;

import java.util.ArrayList;
import java.util.HashMap;

import static mainFiles.Main.calcGPA;
import static mainFiles.Main.getSubjects;

public class Student {

    private static int numOfStudents = 0;

    private String name;
    private int age;
    private final HashMap<Subject, Integer> subjects = new HashMap<>();

    // Constructor Class
    public Student(String name, int age, ArrayList<Subject> subjects) {
        this.name = name;
        this.age = age;
        for (Subject subject : subjects) {
            this.subjects.put(subject, 0); // Initialize with default grade of 0
        }
        numOfStudents++;
        School.addStudent(this, true);
    }
    public Student(String name, int age, HashMap<Subject, Integer> subjectsAndGrades) {
        this.name = name;
        this.age = age;
        this.subjects.putAll(subjectsAndGrades);
        School.addStudent(this, false);
    }

    public void setGrade(Subject subject, int newGrade) {
        if (subjects.containsKey(subject)) {
            subjects.put(subject, newGrade);
            System.out.println("Grade updated: " + subject.getName() + " = " + newGrade);
        }
    }

    @Override
    public String toString() {
        StringBuilder subjectsStr = new StringBuilder();
        ArrayList<Subject> Subjects = getSubjects(this);
        for (int i = 0; i < this.subjects.size(); i++) {
            if(i+1 == this.subjects.size()){
                subjectsStr.append(Subjects.get(i).getName()).append(".");
            }
            else{
                subjectsStr.append(Subjects.get(i).getName()).append(", ");
            }
        }
        return "name: " + this.name + " | Age: " + this.age + " | GPA: " + calcGPA(this) + "% | Subjects: " + subjectsStr;
    }

    // Getter methods
    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }


    public HashMap<Subject, Integer> getSubjectsAndGrades() {
        return this.subjects;
    }

    public static int getNumOfStudents() {
        return numOfStudents;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public static void minusNumOfStudents() {
        numOfStudents--;
    }

    public void addSubject(Subject subject) {
        this.subjects.put(subject, 0);
    }

    public void removeSubject(Subject subject) {
        this.subjects.remove(subject);
    }

    public static void setNumOfStudents(int latestNumOfStudents) {
        Student.numOfStudents = latestNumOfStudents;
    }
}
