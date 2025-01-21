import java.util.ArrayList;

public class Subject {

    private String name;
    private int marks;
    private static ArrayList<Subject> availableSubjects = new ArrayList<>();

    Subject(String name, int marks){
        this.name = name;
        this.marks = marks;
        availableSubjects.add(this);
    }

    @Override
    public String toString(){
        return this.name + " : " + "Mark is out of: " + this.marks;
    }

    //Getter methods
    public static ArrayList<Subject> getSubjects(){
        return availableSubjects;
    }

    //setter method
}
