import java.util.Scanner;
import support_services.equipment_inventory_manager.equipmentInventoryManager;

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
    String choice = scanner.nextLine().trim();
    
    switch (choice) {
        case "1" -> callAdmistrativeServices();
        case "2" -> callDiagnosticServices();
        case "3" -> callMedicalServices();
        case "4" -> callNursingServices();
        case "5" -> callSupportServices();
        default -> running = false;
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
        equipmentInventoryManager.main(new String[]{}); 
    }
}