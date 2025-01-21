import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main_menu {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Subject> PlaceHolderSubjects = new ArrayList<>(Arrays.asList(new Subject("English", 40), new Subject("Math", 60)));

    public static void main(String[] args) throws InterruptedException {

        Student studentTest = new Student("Youssef Hany Galal", 18, 3.4, PlaceHolderSubjects);

        boolean is_running = true;

        while(is_running){
            printMenu();
            System.out.print("Choose an option: ");
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); //clear buffer
            }
            catch (InputMismatchException e) {
                scanner.nextLine();
                continue;
            }

            switch (choice){
                case 1 -> AddStudent();
                case 2 -> RemoveStudent();
                case 3 -> ViewAllStudents();
                case 4 -> SearchForStudent();
                default -> is_running = false;
            }
        }

        scanner.close();
    }
    public static void printMenu(){
        System.out.println("---------------");
        System.out.println("Student Manager");
        System.out.println("---------------");
        System.out.println("1) Add a new student.");
        System.out.println("2) Remove a student.");
        System.out.println("3) View all students.");
        System.out.println("4) Search for a student.");
        System.out.println("5) Exit the program");
        System.out.println("---------------");
    }

    public static void AddStudent(){
        System.out.println("-------------");
        System.out.println("Add a Student");
        System.out.println("-------------");

        System.out.print("Student's name: ");
        String name = scanner.nextLine();

        System.out.print("Student's Age: ");
        int age = scanner.nextInt();

        System.out.print("Student's GPA: ");
        double GPA = scanner.nextDouble();
        scanner.nextLine(); //clear buffer

        Student student = new Student(name, age, GPA, PlaceHolderSubjects);
        System.out.println("Student added Successfully!");
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    public static void RemoveStudent(){
        System.out.println("----------------");
        System.out.println("Remove a Student");
        System.out.println("----------------");

        System.out.print("Enter ID of student: ");
        int ID = scanner.nextInt();
        scanner.nextLine(); //clear buffer
        School.removeStudent(ID);

        System.out.println("Student removed Successfully");
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    public static void ViewAllStudents(){
        System.out.println("-----------------");
        System.out.println("View All Students");
        System.out.println("-----------------");

        System.out.println("Numbers of students: "+ Student.getNumOfStudents());
        School.printList();
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    public static void SearchForStudent() throws InterruptedException {
        System.out.println("-----------------");
        System.out.println("Search for Student");
        System.out.println("-----------------");
        System.out.print("Enter the ID of student: ");
        int ID = 0;
        boolean id_found = true;
        try {
            ID = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            id_found = false;
        }
        finally {
            scanner.nextLine(); //clear buffer
        }

        Student student = School.getStudentObj(ID);

        if (id_found && student != null)
        {
            studentModify(student);
        }
        else if(student == null){
            System.out.println("Student was not found.");
            Thread.sleep(2000);
        }
        else {
            System.out.println("Please enter a valid number.");
            Thread.sleep(2000);
        }
    }

    static void studentModify(Student student) throws InterruptedException {

        boolean run_studentmodify = true;
        while (run_studentmodify)
        {
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("GPA: " + student.getGPA());
            System.out.println("Subjects: ");
            System.out.println();

            System.out.print("What would you like to change (q to quit): ");
            String user = scanner.nextLine().toLowerCase();
            switch (user)
            {
                case "name" -> modifyName(student);
                case "age" -> modifyAge(student);
                case "gpa" -> modifyGPA(student);
                case "q" -> {
                    System.out.println("Going back!");
                    Thread.sleep(2000);
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

    private static void modifyGPA(Student student) throws InterruptedException {
        System.out.print("Please enter the new GPA (0 to quit): ");
        while (true) {
            double newGPA = 0;
            try {
                newGPA = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number.");
                Thread.sleep(2000);
                continue;
            } finally {
                scanner.nextLine(); //clear buffer
            }
            if(newGPA == 0){
                System.out.println("Going back!");
                break;
            }
            student.setGPA(newGPA);
            System.out.println("Successfully changed GPA to: " + newGPA);
            System.out.print("Press enter to continue...");
            scanner.nextLine();
            break;
        }
    }

    private static void modifyAge(Student student) throws InterruptedException {
        while(true)
        {
            int newAge = 0;
            try {
                System.out.print("Please enter the new (0 to quit): ");
                newAge = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                Thread.sleep(2000);
                continue;
            } finally {
                scanner.nextLine(); //clear buffer
            }
            if (newAge == 0) {
                break;
            }
            student.setAge(newAge);
            System.out.println("Successfully changed age to: " + newAge);
            System.out.print("Press enter to continue...");
            scanner.nextLine();
            break;
        }
    }

    private static void modifyName(Student student) throws InterruptedException {
        while(true) {
            System.out.print("Please enter the new name (q to quit): ");
            String newName = scanner.nextLine();
            if (newName.equals("q")) {
                System.out.println("Going back!");
                Thread.sleep(2000);
                break;
            } else if (newName.isEmpty()) {
                System.out.println("Please enter a string.");
                Thread.sleep(2000);
                continue;
            }
            student.setName(newName);
            System.out.println("Successfully changed name to: " + newName);
            System.out.print("Press enter to continue...");
            scanner.nextLine();
            break;
        }
    }
}
