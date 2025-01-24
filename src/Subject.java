import java.util.ArrayList;

public class Subject {

    private final String name;
    private final int marks;
    private static ArrayList<Subject> availableSubjects = new ArrayList<>();

    //Constructor Class
    Subject(String name, int marks){
        this.name = name;
        this.marks = marks;
        availableSubjects.add(this);
    }

    @Override
    public String toString(){
        return this.name;
    }

    //Getter methods
    public static ArrayList<Subject> getSubjects(){
        return availableSubjects;
    }

    public static Subject get_subject(int i){
        return availableSubjects.get(i);
    }

    public int getMarks(){
        return this.marks;
    }

    //setter method
    public static void setAvailableSubjects(ArrayList<Subject> loaded_subjects){
        availableSubjects = loaded_subjects;
    }
    public static void removeSubject(int i){
        availableSubjects.remove(i);
    }
}
