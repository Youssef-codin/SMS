import java.util.Scanner;

public class Main_menu {

    private final static Scanner scanner = new Scanner(System.in);
    final static int waitTime_in_ms = 1500;

    //PRINT METHODS
    //used in main method.
    private static void print_mainMenu(){
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

    //used in ModifySubjects method.
    private static void print_subjectsMenu() {
        System.out.println("---------");
        System.out.println("Subjects");
        if (!Subject.getSubjects().isEmpty())
            for (int i = 1; i <= Subject.getSubjects().size(); i++)
                System.out.println(i + ") " + Subject.getSubjects().get(i-1));
        System.out.println("---------");
        System.out.println("1) Add Subject.");
        System.out.println("2) Remove Subject.");
        System.out.println("3) Edit Subject.");
        System.out.println("0) Quit");
        System.out.println("---------");
    }

    public static void main(String[] args) throws InterruptedException {

//        Student studentTest = new Student("Youssef Hany Galal", 18, 3.4, );

        boolean is_running = true;

        while(is_running){
            print_mainMenu();
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

        String user = inputValidation.safeString("Student's name: ");

        int age = inputValidation.safeInt("Student's Age: ");

        double GPA = inputValidation.safeDouble("Student's GPA: ");

//        Student student = new Student(name, age, GPA, );
        System.out.println("Student added Successfully!");
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    //METHOD TO REMOVE STUDENT FROM SCHOOL
    //used in main method.
    private static void RemoveStudent() throws InterruptedException {
        System.out.println("----------------");
        System.out.println("Remove a Student");
        System.out.println("----------------");

        int ID = inputValidation.safeInt("Enter ID of student: ");
        School.removeStudent(ID);

        //need to make it if there was no student in the first place.

        System.out.println("Student removed Successfully");
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    //METHOD TO SEE ALL STUDENTS IN THE SCHOOL
    //used in main method.
    private static void ViewAllStudents(){
        System.out.println("-----------------");
        System.out.println("View All Students");
        System.out.println("-----------------");

        System.out.println("Numbers of students: "+ Student.getNumOfStudents());
        School.printList();
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

        Student student = School.getStudentObj(ID);

        if(student == null){
            System.out.println("Student was not found.");
            Thread.sleep(waitTime_in_ms);
        }
        else {
            ModifyStudent(student);
        }
    }


    //METHOD TO MODIFY STUDENT'S NAME, AGE, GPA AND SUBJECTS/GRADES
    //used in SearchForStudent method.
    private static void ModifyStudent(Student student) throws InterruptedException {

        boolean run_studentmodify = true;
        while (run_studentmodify)
        {
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("GPA: " + student.getGPA());
            System.out.println("Subjects: ");
            System.out.println();

            String user = inputValidation.safeString("What would you like to change (q to quit): ").toLowerCase();
            switch (user)
            {
                case "name" -> modifyName(student);
                case "age" -> modifyAge(student);
                case "gpa" -> modifyGPA(student);
                case "q" -> {
                    System.out.println("Going back!");
                    Thread.sleep(waitTime_in_ms);
                    run_studentmodify = false;
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
    private static void modifyName(Student student) throws InterruptedException {

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

    private static void modifyAge(Student student) throws InterruptedException {
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

    private static void modifyGPA(Student student) throws InterruptedException {

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


    //MAIN METHOD TO MODIFY THE AVAILABLE SUBJECTS
    //used in main method.
    private static void ModifySubjects() throws InterruptedException {

        boolean is_running = true;
        while (is_running) {
            print_subjectsMenu();
            int choice = inputValidation.safeInt("Choose an option (1/2/3/0): ");

            switch (choice) {
                case 1 -> addSubject();
                case 2 -> RemoveSubject();
                case 3 -> EditSubjects();
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

    //SUBJECT METHODS TO ADD REMOVE OR EDIT
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
                Subject subject = new Subject(subjectName, subjectMarks);
                System.out.println("Successfully added " + subjectName + " with " + subjectMarks + " marks.");
                System.out.print("Press enter to continue...");
                scanner.nextLine();
            } else {
                System.out.println("Going back!");
                Thread.sleep(waitTime_in_ms);
            }
        }
    }

    private static void RemoveSubject(){

    }

    private static void EditSubjects(){

    }
}

