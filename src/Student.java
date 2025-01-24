import java.util.ArrayList;
import java.util.HashMap;

public class Student {

    private static int numOfStudents = 0;

    private String name;
    private int age;
    private int GPA;
    private final HashMap<Subject, Integer> subjects = new HashMap<>();

    // Constructor Class
    public Student(String name, int age, ArrayList<Subject> subjects) {
        this.name = name;
        this.age = age;
        this.GPA = 0;
        for (Subject subject : subjects) {
            this.subjects.put(subject, 0); // Initialize with default grade of 0
        }
        numOfStudents++;
        School.addStudent(this, true);
    }
    public Student(String name, int age, HashMap<Subject, Integer> subjectsAndGrades) {
        this.name = name;
        this.age = age;
        this.GPA = 0;
        this.subjects.putAll(subjectsAndGrades);
        School.addStudent(this, false);
    }

    public static class SubjectEntry {
        public String name;
        public int marks;

        public SubjectEntry(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }
    }

    public ArrayList<SubjectEntry> getSubjectEntries() {
        ArrayList<SubjectEntry> entries = new ArrayList<>();
        for (Subject subject : subjects.keySet()) {
            entries.add(new SubjectEntry(subject.getName(), subjects.get(subject))); // Corrected to use the grade
        }
        return entries;
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
        for (Subject subject : subjects.keySet()) {
            subjectsStr.append(subject.getName()).append(", ");
        }
        return "name: " + this.name + " | Age: " + this.age + " | GPA: " + this.GPA + " | Subjects: " + subjectsStr;
    }

    // Getter methods
    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getGPA() {
        return this.GPA;
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

    public void setGPA(int GPA) {
        this.GPA = GPA;
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
