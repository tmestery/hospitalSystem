package medical_services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import medical_services.request_test.Doctor;
import medical_services.request_test.Patient;
import medical_services.schedule.AppointmentController;
import medical_services.schedule.AppointmentUI;

public class MedicalUseCaseSelector {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Medical Services Use Cases ===");
        System.out.println("1) Request Diagnostic Test");
        System.out.println("2) Schedule Follow-Up Appointment");
        System.out.print("Enter choice (1 or 2): ");

        String choice = sc.nextLine().trim();

        switch (choice) {
            case "1" -> {
                
                MedMain.main(new String[] {});
            }
            case "2" -> {
                
                runScheduleAppointment(sc);
            }
            default -> System.out.println("Invalid choice. Exiting Medical Services.");
        }

        sc.close();
    }

    private static void runScheduleAppointment(Scanner sc) {

        
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(UUID.randomUUID(), "Ayan Asim", LocalDate.of(2002, 5, 12)));
        patients.add(new Patient(UUID.randomUUID(), "Asim Qureshi", LocalDate.of(1980, 6, 6)));
        patients.add(new Patient(UUID.randomUUID(), "Hira Asim", LocalDate.of(1980, 1, 12)));

       
        System.out.println("=== Doctor Sign-in ===");
        System.out.print("Enter your name: ");
        String docName = sc.nextLine().trim();
        System.out.print("Enter your specialty: ");
        String docSpec = sc.nextLine().trim();
        UUID doctorId = UUID.randomUUID();
        Doctor doc = new Doctor(doctorId, docName, docSpec);
        System.out.println("Welcome, Dr. " + docName + " (" + docSpec + ").");

        
        AppointmentController controller = new AppointmentController(patients);
        AppointmentUI ui = new AppointmentUI(controller, sc);
        ui.startScheduleAppointment(doc);
    }
}