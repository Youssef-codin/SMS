import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/*To do
   At modify students I need to notify the user when subject is already there or not
   GPA calculation
 */

public class Main_menu {

    private final static Scanner scanner = new Scanner(System.in);
    final static int waitTime_in_ms = 800;

    public static void main(String[] args) throws InterruptedException {

        //Used for testing till i make save methods.
        Subject subjectTest1 = new Subject("English 1", 40);
        Subject subjectTest2 = new Subject("Math 1", 60);
        new Subject("Java OOP", 50);
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
        String subjectChoices = inputValidation.safeString("Please pick the subjects you'd like to add separated by commas (0 to quit): ");
        subjectChoices = subjectChoices.replace(" ", "").replace(",", ""); //123

        if(subjectChoices.charAt(0) == '0'){
            System.out.println("Going back!");
            Thread.sleep(waitTime_in_ms);
        }
        else if(subjectChoices.length() > Subject.getSubjects().size() || !inputValidation.onlyDigits(subjectChoices)){
            System.out.println("Enter Valid choices.");
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
            print.studentDetails(student);

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
                    Thread.sleep(waitTime_in_ms);
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
                    Thread.sleep(waitTime_in_ms);
                    is_running = false;
                }
                default -> {
                    System.out.println("Please enter a valid input");
                    Thread.sleep(waitTime_in_ms);
                }
            }
        }
    }

    //METHODS TO MODIFY STUDENT'S SUBJECT STUFF
    //used in modifyStudent_Subjects
    private static void addStudentSubject(Student student) throws InterruptedException {
        print.titleAndSubjects();
        int subjectChoice = inputValidation.safeInt("Please pick the subject you'd like to add: ");
        if (subjectChoice != 0){
            student.addSubject(Subject.get_subject(subjectChoice - 1));
            System.out.println("Subject added.");
            Thread.sleep(waitTime_in_ms);
        }
        else {
            System.out.println("Going back!");
            Thread.sleep(waitTime_in_ms);
        }
    }

    private static void removeStudentSubject(Student student) throws InterruptedException {
        print.titleAndSubjects();
        int subjectChoice = inputValidation.safeInt("Please pick the subject you'd like to remove: ");
        if (subjectChoice != 0) {
            student.removeSubject(Subject.get_subject(subjectChoice - 1));
            System.out.println("Subject removed.");
            Thread.sleep(waitTime_in_ms);
        }
        else {
            System.out.println("Going back!");
            Thread.sleep(waitTime_in_ms);
        }
    }
    private static void changeStudentGrade(Student student) throws InterruptedException {
        int i = 1;
        ArrayList<Subject> tempList = new ArrayList<>();

        for(Map.Entry<Subject, Integer> entry : student.getSubjectsAndGrades().entrySet()){
            tempList.add(entry.getKey());

            Subject subject = entry.getKey();
            Integer grade = entry.getValue();
            System.out.println(i + ") " + subject + " : " + grade + "/" + subject.getMarks());
            i++;
        }
        System.out.println("-------------------");

        int subjectChoice = inputValidation.safeInt("Please pick the subject you'd like to change grade of: ");
        if(subjectChoice > tempList.size()){
            System.out.println("Pick a valid option.");
            Thread.sleep(waitTime_in_ms);
        }
        else if (subjectChoice != 0) {
            int newGrade = inputValidation.safeInt("Enter the new grade: ");
            student.setGrade(tempList.get(subjectChoice - 1), newGrade);

            System.out.println("New grade set.");
            Thread.sleep(waitTime_in_ms);
        }
        else {
            System.out.println("Going back!");
            Thread.sleep(waitTime_in_ms);
        }
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