import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main_menu {

    private final static Scanner scanner = new Scanner(System.in);
    final static int waitTime_in_ms = 800;

    public static void main(String[] args) throws InterruptedException {

        Subject subjectTest1 = new Subject("English 1", 40);
        Subject subjectTest2 = new Subject("Math 1", 60);
        new Student("Youssef Hany Galal", 18, 3.4, new ArrayList<>(Arrays.asList(subjectTest1, subjectTest2)));

        boolean is_running = true;

        while(is_running){
            print.mainMenu();
            int choice = inputValidation.safeInt("Choose an option: ");

            switch (choice){
                case 1 -> AddStudent();
                case 2 -> RemoveStudent();
                case 3 -> ViewAllStudents();
                case 4 -> SearchForStudent();
                case 5 -> ModifySubjects();
                case 0 -> is_running = false;
                default -> {
                    System.out.println("Please enter a valid choice.");
                    Thread.sleep(waitTime_in_ms);
                }
            }
        }
        //Clean up
        scanner.close();
        inputValidation.scanner.close();
    }

    //METHOD USED TO ADD STUDENT TO SCHOOL
    //used in main method.
    private static void AddStudent() throws InterruptedException {
        System.out.println("-------------");
        System.out.println("Add a Student");
        System.out.println("-------------");

        String name = inputValidation.safeString("Student's name: ");

        int age = inputValidation.safeAge("Student's Age: ");

        double GPA = inputValidation.safeDouble("Student's GPA: ");

        print.titleAndSubjects();
        String subjectChoices = inputValidation.safeString("Please pick the subjects you'd like to add: ");
        subjectChoices = subjectChoices.trim().replace(",", ""); //123

        if(subjectChoices.length() > Subject.getSubjects().size()){
            System.out.println("Chose Too Many Subjects.");
            Thread.sleep(waitTime_in_ms);
        }
        else {
            ArrayList<Subject> chosenSubjects = new ArrayList<>();

            for (int i = 0; i < subjectChoices.length(); i++) {
                chosenSubjects.add(Subject.get_subject(Character.getNumericValue(subjectChoices.charAt(i)) - 1));
            }

            new Student(name, age, GPA, chosenSubjects);
            System.out.println("Student added Successfully!");
            System.out.print("Press enter to continue...");
            scanner.nextLine();
        }
    }

    //METHOD TO REMOVE STUDENT FROM SCHOOL
    //used in main method.
    private static void RemoveStudent() throws InterruptedException {
        System.out.println("----------------");
        System.out.println("Remove a Student");
        System.out.println("----------------");

        int ID = inputValidation.safeInt("Enter ID of student: ");

        if(School.getStudentObj(ID) == null){
            System.out.println("Student was not found.");
            Thread.sleep(waitTime_in_ms);
        }
        else {
            School.removeStudent(ID);
            System.out.println("Student removed Successfully");
            System.out.print("Press enter to continue...");
            scanner.nextLine();
        }
    }

    //METHOD TO SEE ALL STUDENTS IN THE SCHOOL
    //used in main method.
    private static void ViewAllStudents(){
        System.out.println("-----------------");
        System.out.println("View All Students");
        System.out.println("-----------------");

        if(Student.getNumOfStudents() > 0) {
            System.out.println("Numbers of students: " + Student.getNumOfStudents());
            System.out.println("-------------------");
            School.getList();
        }
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    //METHOD TO SEARCH FOR STUDENT AND MODIFY IF USER LIKES TO
    //used in main method.
    private static void SearchForStudent() throws InterruptedException {
        System.out.println("-----------------");
        System.out.println("Search for Student");
        System.out.println("-----------------");

        int ID = inputValidation.safeInt("Enter the ID of student: ");

        if(School.getStudentObj(ID) == null){
            System.out.println("Student was not found.");
            Thread.sleep(waitTime_in_ms);
        }
        else {
            ModifyStudent(School.getStudentObj(ID));
        }
    }


    //METHOD TO MODIFY STUDENT'S NAME, AGE, GPA AND SUBJECTS/GRADES
    //used in SearchForStudent method.
    private static void ModifyStudent(Student student) throws InterruptedException {

        boolean run_studentModify = true;
        while (run_studentModify)
        {
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("GPA: " + student.getGPA());
            System.out.println("Subjects: ");
            print.student_subjects(student);

            String user = inputValidation.safeString("What would you like to change (q to quit): ").toLowerCase();
            switch (user)
            {
                case "name" -> modifyStudent_Name(student);
                case "age" -> modifyStudent_Age(student);
                case "gpa" -> modifyStudent_GPA(student);
                case "subjects" -> modifyStudent_Subjects(student);
                case "q" -> {
                    System.out.println("Going back!");
                    Thread.sleep(waitTime_in_ms);
                    run_studentModify = false;
                }
                default -> {
                    System.out.println("Please enter a valid input");
                    System.out.print("Press enter to continue...");
                    scanner.nextLine();
                }
            }
        }
    }

    //STUDENT MODIFY METHODS TO CHANGE NAME, AGE, GPA OR SUBJECTS AND GRADES
    //used in studentModify method.
    private static void modifyStudent_Name(Student student) throws InterruptedException {

        String newName = inputValidation.safeString("Please enter the new name (q to quit): ");
        if (newName.equals("q")) {
            System.out.println("Going back!");
            Thread.sleep(waitTime_in_ms);
        }
        student.setName(newName);
        System.out.println("Successfully changed name to: " + newName);
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    private static void modifyStudent_Age(Student student) throws InterruptedException {
        int newAge = inputValidation.safeInt("Please enter the new (0 to quit): ");

        if (newAge == 0) {
            System.out.println("Going back!");
            Thread.sleep(waitTime_in_ms);
        }
        else {
            student.setAge(newAge);
            System.out.println("Successfully changed age to: " + newAge);
            System.out.print("Press enter to continue...");
            scanner.nextLine();
        }
    }

    private static void modifyStudent_GPA(Student student) throws InterruptedException {

        double newGPA = inputValidation.safeDouble("Please enter the new GPA (0 to quit): ");
        if(newGPA == 0){
            System.out.println("Going back!");
            Thread.sleep(waitTime_in_ms);
        }
        else {
            student.setGPA(newGPA);
            System.out.println("Successfully changed GPA to: " + newGPA);
            System.out.print("Press enter to continue...");
            scanner.nextLine();
        }
    }

    private static void modifyStudent_Subjects(Student student) throws InterruptedException {
        int choice = inputValidation.safeInt("Which subject would you like to edit?: ");


    }

    //METHODS TO MODIFY STUDENT'S SUBJECT STUFF
    //used in modifyStudent_Subjects


    //MAIN METHOD TO MODIFY THE AVAILABLE SUBJECTS
    //used in main method.
    private static void ModifySubjects() throws InterruptedException {

        boolean is_running = true;
        while (is_running) {
            print.subjectsMenu();
            int choice = inputValidation.safeInt("Choose an option (1/2/3/0): ");

            switch (choice) {
                case 1 -> addSubject();
                case 2 -> RemoveSubject();
                case 0 -> {
                    System.out.println("Going back!");
                    Thread.sleep(waitTime_in_ms);
                    is_running = false;
                }
                default -> {
                    System.out.println("Please enter a valid choice.");
                    Thread.sleep(waitTime_in_ms);
                }
            }
        }
    }

    //SUBJECT METHODS TO ADD AND REMOVE
    //used in ModifySubjects method.
    private static void addSubject() throws InterruptedException {

        String subjectName = inputValidation.safeString("Enter the name of the Subject (q to quit): ");

        if(subjectName.equals("q")){
            System.out.println("Going back!");
            Thread.sleep(waitTime_in_ms);
        }
        else{
            int subjectMarks = inputValidation.safeInt("Enter the marks of the Subject (0 to quit): ");

            if (subjectMarks != 0) {
                new Subject(subjectName, subjectMarks);
                System.out.println("Successfully added " + subjectName + " with " + subjectMarks + " marks.");
                System.out.print("Press enter to continue...");
                scanner.nextLine();
            } else {
                System.out.println("Going back!");
                Thread.sleep(waitTime_in_ms);
            }
        }
    }

    private static void RemoveSubject() throws InterruptedException {
        print.titleAndSubjects();
        int choice = inputValidation.safeInt("Choose a subject to remove: ");
        Subject.removeSubject(choice-1);
    }
}