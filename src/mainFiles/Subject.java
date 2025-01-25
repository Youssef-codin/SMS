package mainFiles;

import java.util.ArrayList;

public class Subject {

    private final String name;
    private final int marks;
    private static ArrayList<Subject> availableSubjects = new ArrayList<>();

    //Constructor Class
    public Subject(String name, int maxMarks){
        this.name = name;
        this.marks = maxMarks;
        availableSubjects.add(this);
    }

    //Getter methods
    public String getName() {
        return name;
    }

    //used in deserializers to check if the subjects exist already or not
    public static Subject subjectAlrExists(String name) {
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

    public int getMaxGrade(){
        return this.marks;
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
