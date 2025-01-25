package mainFiles;

import java.util.Map;

import static mainFiles.main.calcGPA;

public abstract class print {
    //PRINT METHODS
    //used in main method.
    public static void mainMenu(){
        System.out.println("---------------");
        System.out.println("Student Manager");
        System.out.println("---------------");
        System.out.println("1) Add a new student.");
        System.out.println("2) Remove a student.");
        System.out.println("3) View all students.");
        System.out.println("4) Search for a student.");
        System.out.println("5) Edit available subjects.");
        System.out.println("0) Exit the program");
        System.out.println("---------------");
    }

    //for print.subjectsMenu, addSubject, removeSubject, addStudentSubject
    public static void titleAndSubjects() {
        System.out.println("---------");
        System.out.println("Subjects");
        if (!Subject.getSubjects().isEmpty())
            for (int i = 1; i <= Subject.getSubjects().size(); i++)
                System.out.println(i + ") " + Subject.getSubjects().get(i-1).getSubjectName());
    }

    //used in ModifySubjects method.
    public static void subjectsMenu() {
        titleAndSubjects();
        System.out.println("---------");
        System.out.println("1) Add Subject.");
        System.out.println("2) Remove Subject.");
        System.out.println("0) Quit");
        System.out.println("---------");
    }

    //used in modifyStudent_Subjects
    public static void modifyStudent_Subjects() {
        System.out.println("-------------------");
        System.out.println("1) Add Subject.");
        System.out.println("2) Remove Subject");
        System.out.println("3) Change Grades. ");
        System.out.println("0) Go back.");
        System.out.println("-------------------");
    }

    //used in ModifyStudent, print.studentDetails and changeStudentGrade
    public static void studentSubjectsAndGrades(Student student) {
        int i = 1;
        for(Map.Entry<Subject, Integer> entry : student.getSubjectsAndGrades().entrySet()){
            Subject subject = entry.getKey();
            Integer grade = entry.getValue();
            System.out.println(i + ") " + subject.getSubjectName() + " : " + grade + "/" + subject.getMaxGrade());
            i++;
        }
        System.out.println("-------------------");
    }

    //used in ModifyStudent
    public static void studentDetails(Student student){
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("Grade: " + calcGPA(student) + "%");
        System.out.println("Subjects: ");
        print.studentSubjectsAndGrades(student);
    }
}
