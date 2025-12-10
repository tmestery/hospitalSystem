package nursing_services.careplan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import nursing_services.schedule.Patient;
import nursing_services.schedule.Nurse;

public class CarePlanSystem {

    private Map<String, List<CarePlanEntry>> plans = new HashMap<>();

    private static final String[] CATEGORIES = {
        "Movement", "Nutrition", "Hygiene", "Medicational support",
        "Pain Management", "Wound Care", "Emotional Support",
        "Safety / Fall Risk", "Other"
    };

    public void updateCarePlan(Patient patient, Nurse nurse, Scanner sc) {

        System.out.println("Add or Update Clinical Care Plan...");
        System.out.println("Choose Care Plan Category:");

        for (int i = 0; i < CATEGORIES.length; i++) {
            System.out.println((i + 1) + ") " + CATEGORIES[i]);
        }

        System.out.print("Enter choice: ");
        String category = CATEGORIES[Integer.parseInt(sc.nextLine()) - 1];

        System.out.print("Enter care plan update description: ");
        String desc = sc.nextLine();

        CarePlanEntry entry = new CarePlanEntry(category, desc);
        plans.computeIfAbsent(patient.getPatientID(), k -> new ArrayList<>()).add(entry);

        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm:ss"));
        String[] dt = timestamp.split(",");

        CarePlanUpdate update = new CarePlanUpdate(
                patient.getPatientID(),
                patient.getName(),
                nurse.getNurseID(),
                nurse.getName(),
                category,
                desc,
                dt[0],
                dt[1]
        );

        CarePlanStore.appendCarePlanUpdate(update);

        // ALWAYS ask for progress:
        System.out.println("progress level:");
        System.out.println("1) Not Started");
        System.out.println("2) In Progress");
        System.out.println("3) Partially Achieved");
        System.out.println("4) Achieved");
        System.out.println("5) Needs Revision");
        System.out.print("Choose level: ");
        int level = Integer.parseInt(sc.nextLine());
        entry.setProgressLevel(level);

        System.out.println("Progress updated!\n");
    }

    public void updateProgressOnly(Patient patient, Scanner sc) {

        List<CarePlanEntry> entries = plans.get(patient.getPatientID());
        if (entries == null || entries.isEmpty()) {
            System.out.println("No care plan entries in this session.");
            return;
        }

        System.out.println("Existing Care Plan Entries:");
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ") " + entries.get(i));
        }

        System.out.print("Select entry to update: ");
        int index = Integer.parseInt(sc.nextLine()) - 1;
        CarePlanEntry entry = entries.get(index);

        System.out.println(" progress level:");
        System.out.println("1) Not Started");
        System.out.println("2) In Progress");
        System.out.println("3) Partially Achieved");
        System.out.println("4) Achieved");
        System.out.println("5) Needs Revision");

        int level = Integer.parseInt(sc.nextLine());
        entry.setProgressLevel(level);

        System.out.println("Progress updated.\n");
    }

    public void viewCarePlan(String patientId) {

        System.out.println("Care Plan Records: ");

        var saved = CarePlanStore.loadCarePlans(patientId);

        if (saved.isEmpty()) System.out.println("No saved entries.");
        else {
            for (CarePlanUpdate u : saved) {
                System.out.println("- [" + u.getDate() + " " + u.getTime()
                        + "] " + u.getType() + ": " + u.getDescription());
            }
        }

        System.out.println("Care Plan Entries: ");

        var session = plans.get(patientId);
        if (session == null || session.isEmpty()) System.out.println("No in-session entries.");
        else {
            for (CarePlanEntry e : session) System.out.println(e);
        }
    }
}
