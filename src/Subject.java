import java.util.ArrayList;

public class Subject {

    private final String name;
    private final int marks;
    private static ArrayList<Subject> availableSubjects = new ArrayList<>();

    //Constructor Class
    public Subject(String name, int marks){
        this.name = name;
        this.marks = marks;
        availableSubjects.add(this);
    }

    //Getter methods
    public String getName() {
        return name;
    }

    public static Subject getSubjectByName(String name) {
        for (Subject subject : availableSubjects) {
            if (subject.getName().equals(name)) {
                return subject;
            }
        }
        return null;
    }

    public static ArrayList<Subject> getSubjects(){
        return availableSubjects;
    }

    public static Subject get_subject(int i){
        return availableSubjects.get(i);
    }

    public int getMarks(){
        return marks;
    }

    //setter method
    public static void setAvailableSubjects(ArrayList<Subject> loadedSubjects) {
        availableSubjects.clear();
        availableSubjects.addAll(loadedSubjects);
    }
    public static void removeSubject(int i){
        availableSubjects.remove(i);
    }

    public String getSubjectName() {
        return this.name;
    }
}
