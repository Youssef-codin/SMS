package mainFiles;

import Data.dataHandler;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class main {

    private final static Scanner scanner = new Scanner(System.in);
    public final static int WAIT_TIME_IN_MS = 800;

    public static void main(String[] args) throws InterruptedException {

        dataHandler.loadData();
        dataHandler.loadSubjects();
        dataHandler.loadSchool();

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
                case 0 -> {
                    dataHandler.saveAndLoadALL();
                    is_running = false;
                }
                default -> {
                    System.out.println("Please enter a valid choice.");
                    Thread.sleep(WAIT_TIME_IN_MS);
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

        print.titleAndSubjects();
        String subjectChoices = inputValidation.safeString("Please pick the subjects you'd like to add separated by commas (0 to quit): ");
        subjectChoices = subjectChoices.replace(" ", "").replace(",", "");

        if(subjectChoices.charAt(0) == '0'){
            System.out.println("Going back!");
            Thread.sleep(WAIT_TIME_IN_MS);
        }
        else if(subjectChoices.length() > Subject.getSubjects().size() || !inputValidation.onlyDigits(subjectChoices)){
            System.out.println("Enter Valid choices.");
            Thread.sleep(WAIT_TIME_IN_MS);
        }

        else {
            ArrayList<Subject> chosenSubjects = new ArrayList<>();

            for (int i = 0; i < subjectChoices.length(); i++) {
                chosenSubjects.add(Subject.get_subject(Character.getNumericValue(subjectChoices.charAt(i)) - 1));
            }
            new Student(name, age, chosenSubjects);

            System.out.println("Student added Successfully!");
            System.out.print("Press enter to continue...");
            scanner.nextLine();
        }
        dataHandler.saveAndLoadALL();
    }

    //METHOD TO REMOVE STUDENT FROM SCHOOL
    //used in main method.
    private static void RemoveStudent() throws InterruptedException {
        System.out.println("----------------");
        System.out.println("Remove a Student");
        System.out.println("----------------");

        int ID = inputValidation.safeInt("Enter ID of student: ");
        String stringID = Integer.toString(ID);

        if(School.getStudentObj(stringID) == null){
            System.out.println("Student was not found.");
            Thread.sleep(WAIT_TIME_IN_MS);
        }

        else {
            School.removeStudent(stringID);
            System.out.println("Student removed Successfully");
            System.out.print("Press enter to continue...");
            scanner.nextLine();
        }
        dataHandler.saveAndLoadALL();
    }

    //METHOD TO SEE ALL STUDENTS IN THE SCHOOL
    //used in main method.
    private static void ViewAllStudents() throws InterruptedException {
        System.out.println("-----------------");
        System.out.println("View All Students");
        System.out.println("-----------------");

        if(Student.getNumOfStudents() <= 0) {
            System.out.println("No students found.");
            System.out.println("-------------------");
            Thread.sleep(WAIT_TIME_IN_MS);
        }

        else {
            System.out.println("Numbers of students: " + Student.getNumOfStudents());
            System.out.println("-------------------");
            School.getList();
            System.out.println("-------------------");
            System.out.print("Press enter to continue...");
            scanner.nextLine();
        }
    }

    //METHOD TO SEARCH FOR STUDENT AND MODIFY IF USER LIKES TO
    //used in main method.
    private static void SearchForStudent() throws InterruptedException {
        System.out.println("-----------------");
        System.out.println("Search for Student");
        System.out.println("-----------------");

        int ID = inputValidation.safeInt("Enter the ID of student: ");
        String stringID = Integer.toString(ID);

        if(School.getStudentObj(stringID) == null){
            System.out.println("Student was not found.");
            Thread.sleep(WAIT_TIME_IN_MS);
        }
        else {
            ModifyStudent(School.getStudentObj(stringID));
        }
    }


    //METHOD TO MODIFY STUDENT'S NAME, AGE AND SUBJECTS/GRADES
    //used in SearchForStudent method.
    private static void ModifyStudent(Student student) throws InterruptedException {

        boolean run_studentModify = true;
        while (run_studentModify)
        {
            print.studentDetails(student);

            String user = inputValidation.safeString("What would you like to change (q to quit enter name of what you want): ").toLowerCase();
            switch (user)
            {
                case "name" -> modifyStudent_Name(student);
                case "age" -> modifyStudent_Age(student);
                case "subjects",
                     "subject",
                     "grades",
                     "grade" -> modifyStudent_Subjects(student);
                case "q" -> {
                    System.out.println("Going back!");
                    Thread.sleep(WAIT_TIME_IN_MS);
                    run_studentModify = false;
                }
                default -> {
                    System.out.println("Please enter a valid input");
                    Thread.sleep(WAIT_TIME_IN_MS);
                }
            }
        }
    }

    //STUDENT MODIFY METHODS TO CHANGE NAME, AGE OR SUBJECTS AND GRADES
    //used in studentModify method.
    private static void modifyStudent_Name(Student student) throws InterruptedException {
        String newName = inputValidation.safeString("Please enter the new name (q to quit): ");

        if (newName.equals("q")) {
            System.out.println("Going back!");
            Thread.sleep(WAIT_TIME_IN_MS);
        }

        else {
            student.setName(newName);
            System.out.println("Successfully changed name to: " + newName);
            System.out.print("Press enter to continue...");
            scanner.nextLine();
        }
        dataHandler.saveAndLoadALL();
    }

    private static void modifyStudent_Age(Student student) throws InterruptedException {
        int newAge = inputValidation.safeInt("Please enter the new (0 to quit): ");

        if (newAge == 0) {
            System.out.println("Going back!");
            Thread.sleep(WAIT_TIME_IN_MS);
        }

        else {
            student.setAge(newAge);
            System.out.println("Successfully changed age to: " + newAge);
            System.out.print("Press enter to continue...");
            scanner.nextLine();
        }
        dataHandler.saveAndLoadALL();
    }

    private static void modifyStudent_Subjects(Student student) throws InterruptedException {
        boolean is_running = true;
        while (is_running) {
            print.modifyStudent_Subjects();

            int choice = inputValidation.safeInt("What would you like to do? (0 to quit): ");
            switch (choice) {
                case 1 -> addStudentSubject(student);
                case 2 -> removeStudentSubject(student);
                case 3 -> changeStudentGrade(student);
                case 0 -> {
                    System.out.println("Going back!");
                    Thread.sleep(WAIT_TIME_IN_MS);
                    is_running = false;
                }
                default -> {
                    System.out.println("Please enter a valid input");
                    Thread.sleep(WAIT_TIME_IN_MS);
                }
            }
        }
        dataHandler.saveAndLoadALL();
    }

    //METHODS TO MODIFY STUDENT'S SUBJECT STUFF
    //used in modifyStudent_Subjects
    private static void addStudentSubject(Student student) throws InterruptedException {
        print.titleAndSubjects();
        int subjectChoice = inputValidation.safeInt("Please pick the subject you'd like to add (0 to quit): ");

        if(subjectChoice > Subject.getSubjects().size()){
            System.out.println("Pick a valid option.");
            Thread.sleep(WAIT_TIME_IN_MS);
        }
        else if(subjectChoice == 0){
            System.out.println("Going back!");
            Thread.sleep(WAIT_TIME_IN_MS);
        }

        else {
            ArrayList<Subject> studentSubjects = getSubjects(student);
            Subject subject = Subject.get_subject(subjectChoice - 1);

            if (studentSubjects.contains(subject)) {
                System.out.println("Student already enrolled in " + subject);
                Thread.sleep(WAIT_TIME_IN_MS);
            }
            else{
                student.addSubject(subject);

                System.out.println("Subject " + subject.getName() + " has been added.");
                System.out.print("Press enter to continue...");
                scanner.nextLine();
            }
        }
        dataHandler.saveAndLoadALL();
    }

    private static void removeStudentSubject(Student student) throws InterruptedException {
        ArrayList<Subject> studentSubjects = getSubjects(student);
        print.studentSubjectsAndGrades(student);

        int subjectChoice = inputValidation.safeInt(
        "Please pick the subject you'd like to remove (0 to go back): ");

        if(subjectChoice > studentSubjects.size()){
            System.out.println("Pick a valid option.");
            Thread.sleep(WAIT_TIME_IN_MS);
        }
        else if (subjectChoice == 0) {
            System.out.println("Going back!");
            Thread.sleep(WAIT_TIME_IN_MS);
        }

        else {
            Subject subject = studentSubjects.get(subjectChoice - 1);
            student.removeSubject(subject);

            System.out.println("Subject " + subject.getName() + " has been removed.");
            System.out.print("Press enter to continue...");
            scanner.nextLine();
        }
        dataHandler.saveAndLoadALL();
    }

    private static void changeStudentGrade(Student student) throws InterruptedException {
        ArrayList<Subject> studentSubjects = getSubjects(student);
        print.studentSubjectsAndGrades(student);

        int subjectChoice = inputValidation.safeInt("Please pick the subject you'd like to change grade of: ");
        if(subjectChoice > studentSubjects.size()){
            System.out.println("Pick a valid option.");
            Thread.sleep(WAIT_TIME_IN_MS);
        }

        else if (subjectChoice == 0) {
            System.out.println("Going back!");
            Thread.sleep(WAIT_TIME_IN_MS);
        }

        else {
            int newGrade = inputValidation.safeInt("Enter the new grade: ");
            student.setGrade(studentSubjects.get(subjectChoice - 1), newGrade);

            System.out.println("New grade set.");
            System.out.print("Press enter to continue...");
            scanner.nextLine();
        }
        dataHandler.saveAndLoadALL();
    }


    //Helper methods for student Subjects and GPA calculation
    public static ArrayList<Subject> getSubjects(Student student) {
        ArrayList<Subject> Subjects = new ArrayList<>();

        for(Map.Entry<Subject, Integer> entry : student.getSubjectsAndGrades().entrySet()){
            Subjects.add(entry.getKey());
        }
        return Subjects;
    }

    //GPA calculation methods
    private static double getStudentGrades(Student student) {
        double total = 0;

        for(double grade : new ArrayList<>(student.getSubjectsAndGrades().values())){
            total += grade;
        }

        return total;
    }

    private static double SumOfMaxGrades(Student student){
        double maxGrades = 0;

        for(Subject subject : student.getSubjectsAndGrades().keySet()){
            maxGrades += subject.getMaxGrade();
        }

        return maxGrades;
    }

    public static int calcGPA(Student student){
        double finalGrade = getStudentGrades(student) / SumOfMaxGrades(student) * 100;
        return (int)finalGrade;
    }


    //MAIN METHOD TO MODIFY THE AVAILABLE SUBJECTS
    //used in main method.
    private static void ModifySubjects() throws InterruptedException {

        boolean is_running = true;
        while (is_running) {
            print.subjectsMenu();
            int choice = inputValidation.safeInt("Choose an option (1/2/0): ");

            switch (choice) {
                case 1 -> addSubject();
                case 2 -> RemoveSubject();
                case 0 -> {
                    System.out.println("Going back!");
                    Thread.sleep(WAIT_TIME_IN_MS);
                    is_running = false;
                }
                default -> {
                    System.out.println("Please enter a valid choice.");
                    Thread.sleep(WAIT_TIME_IN_MS);
                }
            }
        }
        dataHandler.saveAndLoadALL();
    }

    //SUBJECT METHODS TO ADD AND REMOVE
    //used in ModifySubjects method.
    private static void addSubject() throws InterruptedException {

        String subjectName = inputValidation.safeString("Enter the name of the Subject (q to quit): ");

        if(subjectName.equals("q")){
            System.out.println("Going back!");
            Thread.sleep(WAIT_TIME_IN_MS);
        }
        else{
            int subjectMarks = inputValidation.safeInt("Enter the marks of the Subject (0 to quit): ");

            if (subjectMarks == 0) {
                System.out.println("Going back!");
                Thread.sleep(WAIT_TIME_IN_MS);
            }

            else {
                new Subject(subjectName, subjectMarks, true);

                System.out.println("Successfully added " + subjectName + " with " + subjectMarks + " marks.");
                System.out.print("Press enter to continue...");
                scanner.nextLine();
            }
        }
        dataHandler.saveAndLoadALL();
    }

    private static void RemoveSubject() throws InterruptedException {
        print.titleAndSubjects();
        int choice = inputValidation.safeInt("Choose a subject to remove (0 to go back): ");

        if(choice > Subject.getSubjects().size()){
            System.out.println("Pick a valid option.");
            Thread.sleep(WAIT_TIME_IN_MS);
        } else if (choice == 0) {
            System.out.println("Going back!");
            Thread.sleep(WAIT_TIME_IN_MS);
        }

        else {
            Subject.removeSubject(choice-1);
            System.out.println("Subject removed");
            System.out.print("Press enter to continue...");
            scanner.nextLine();
        }
        dataHandler.saveAndLoadALL();
    }
}