import java.util.Scanner;

public class main {
    private static Boolean running;

    public static void main(String [] args) {
        running = true;
        while (running) {
            menu();
        }
    }

    public static void menu() {
        System.out.println("---------------------------------Main Menu---------------------------------");
        System.out.println("1. Administrative_Services");
        System.out.println("2. Diagnostic_Services");
        System.out.println("3. Medical_Services");
        System.out.println("4. Nursing_Services");
        System.out.println("5. Support_Services");
        System.out.println("---------------------------------------------------------------------------");
        menuOptions();
    }

    public static void menuOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the service you want to enter (1, 2, 3, 4, 5): ");
        String choice = scanner.next();
        
        if (choice.equals(1)) {

        } else if (choice.equals(2)) {

        } else if (choice.equals(3)) {
            
        } else if (choice.equals(4)) {

        } else if (choice.equals(5)) {

        } else {
            running = false;
        }
    }

    public static void callAdmistrativeServices() {
    }

    public static void callDiagnosticServices() {
    }

    public static void callMedicalServices() {
    }

    public static void callNursingServices() {   
    }

    public static void callSupportServices() {
    }
}