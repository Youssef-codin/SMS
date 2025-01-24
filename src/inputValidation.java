import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class inputValidation {

    static Scanner scanner = new Scanner(System.in);

    public static String safeString(String prompt) throws InterruptedException {

        while (true){
            System.out.print(prompt);
            String string = scanner.nextLine();
            if(string.isEmpty()){
                System.out.println("Can't enter an empty string.");
                Thread.sleep(Main_menu.WAIT_TIME_IN_MS);
                continue;
            }
            return string;
        }
    }

    public static int safeInt(String prompt) throws InterruptedException {
        while(true){
            try {
                System.out.print(prompt);
                int num = scanner.nextInt();
                if(num >= 0){
                    return num;
                }
                else{
                    System.out.println("Number must be positive.");
                    Thread.sleep(Main_menu.WAIT_TIME_IN_MS);
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter a number.");
                Thread.sleep(Main_menu.WAIT_TIME_IN_MS);
            }
            finally {
                scanner.nextLine(); //clear buffer
            }
        }
    }

    public static int safeAge(String prompt) throws InterruptedException {
        while(true){
            try {
                System.out.print(prompt);
                int num = scanner.nextInt();
                if(num > 0){
                    return num;
                }
                else{
                    System.out.println("Age must be positive and not 0.");
                    Thread.sleep(Main_menu.WAIT_TIME_IN_MS);
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter a number.");
                Thread.sleep(Main_menu.WAIT_TIME_IN_MS);
            }
            finally {
                scanner.nextLine(); //clear buffer
            }
        }
    }

    public static boolean onlyDigits(String string) {

        for (int i = 0; i < string.length(); i++) {

            if (!Character.isDigit(string.charAt(i))) {

                return false;
            }
        }
        return true;
    }
}
