
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StaffSchedulingSystem {
    private Scanner scanner;
    private List<Staff> staffList;
    private List<Schedule> schedules;
    private SchedulingUser currentUser;

    public StaffSchedulingSystem() {
        scanner = new Scanner(System.in);
        staffList = new ArrayList<>();
        schedules = new ArrayList<>();
        initializeSampleData();
    }

    private void initializeSampleData() {
        currentUser = new SchedulingUser("U001", "Admin User", "Manager");
        
        staffList.add(new Staff("S001", "Alice Johnson", "alice@example.com"));
        staffList.add(new Staff("S002", "Bob Smith", "bob@example.com"));
        staffList.add(new Staff("S003", "Carol Williams", "carol@example.com"));
    }

    public void run() {
        System.out.println("=== Staff Scheduling System ===");
        System.out.println("Logged in as: " + currentUser.getName() + " (" + currentUser.getRole() + ")\n");
        
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter choice: ");
            
            switch (choice) {
                case 1: createSchedule(); break;
                case 2: addShift(); break;
                case 3: assignStaffToShift(); break;
                case 4: viewSchedules(); break;
                case 5: generateCoverageReport(); break;
                case 6: viewStaff(); break;
                case 7: addStaff(); break;
                case 8: 
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        }
    }

    private void displayMenu() {
        System.out.println("1. Create Schedule");
        System.out.println("2. Add Shift to Schedule");
        System.out.println("3. Assign Staff to Shift");
        System.out.println("4. View Schedules");
        System.out.println("5. Generate Coverage Report");
        System.out.println("6. View Staff");
        System.out.println("7. Add Staff");
        System.out.println("8. Exit");
    }

    private void createSchedule() {
        System.out.println("\n--- Create Schedule ---");
        String periodId = getInput("Enter period ID: ");
        LocalDate startDate = getDateInput("Enter start date (yyyy-MM-dd): ");
        LocalDate endDate = getDateInput("Enter end date (yyyy-MM-dd): ");
        
        Schedule schedule = new Schedule(periodId, startDate, endDate, currentUser);
        schedules.add(schedule);
        System.out.println("Schedule created successfully!");
    }

    private void addShift() {
        if (schedules.isEmpty()) {
            System.out.println("No schedules available. Create a schedule first.");
            return;
        }
        
        System.out.println("\n--- Add Shift ---");
        Schedule schedule = selectSchedule();
        if (schedule == null) return;
        
        String shiftId = getInput("Enter shift ID: ");
        LocalDate date = getDateInput("Enter date (yyyy-MM-dd): ");
        LocalTime startTime = getTimeInput("Enter start time (HH:mm): ");
        LocalTime endTime = getTimeInput("Enter end time (HH:mm): ");
        String role = getInput("Enter role: ");
        String location = getInput("Enter location: ");
        
        Shift shift = new Shift(shiftId, date, startTime, endTime, role, location);
        schedule.addShift(shift);
        System.out.println("Shift added successfully!");
    }

    private void assignStaffToShift() {
        if (schedules.isEmpty()) {
            System.out.println("No schedules available.");
            return;
        }
        
        System.out.println("\n--- Assign Staff to Shift ---");
        Schedule schedule = selectSchedule();
        if (schedule == null) return;
        
        List<Shift> unassigned = schedule.getShifts().stream()
            .filter(s -> s.getAssignedStaff() == null)
            .collect(Collectors.toList());
        
        if (unassigned.isEmpty()) {
            System.out.println("No unassigned shifts available.");
            return;
        }
        
        System.out.println("Unassigned shifts:");
        for (int i = 0; i < unassigned.size(); i++) {
            System.out.println((i + 1) + ". " + unassigned.get(i));
        }
        
        int shiftChoice = getIntInput("Select shift: ") - 1;
        if (shiftChoice < 0 || shiftChoice >= unassigned.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        
        Shift shift = unassigned.get(shiftChoice);
        Staff staff = selectStaff();
        if (staff == null) return;
        
        ConflictCheck check = schedule.validateShift(shift, staff);
        if (!check.isValid()) {
            System.out.println("Cannot assign staff due to conflicts:");
            check.getErrors().forEach(System.out::println);
            return;
        }
        
        shift.assignStaff(staff);
        System.out.println("Staff assigned successfully!");
    }

    private void viewSchedules() {
        if (schedules.isEmpty()) {
            System.out.println("No schedules available.");
            return;
        }
        
        System.out.println("\n--- Schedules ---");
        for (Schedule schedule : schedules) {
            System.out.println("\nPeriod: " + schedule.getPeriodId());
            System.out.println("Dates: " + schedule.getStartDate() + " to " + schedule.getEndDate());
            System.out.println("Status: " + schedule.getStatus());
            System.out.println("Shifts:");
            for (Shift shift : schedule.getShifts()) {
                System.out.println("  " + shift);
            }
        }
    }

    private void generateCoverageReport() {
        if (schedules.isEmpty()) {
            System.out.println("No schedules available.");
            return;
        }
        
        System.out.println("\n--- Generate Coverage Report ---");
        Schedule schedule = selectSchedule();
        if (schedule == null) return;
        
        LocalDate date = getDateInput("Enter date for report (yyyy-MM-dd): ");
        CoverageReport report = schedule.generateCoverageReport(date);
        System.out.println("\n" + report);
    }

    private void viewStaff() {
        System.out.println("\n--- Staff List ---");
        staffList.forEach(System.out::println);
    }

    private void addStaff() {
        System.out.println("\n--- Add Staff ---");
        String staffId = getInput("Enter staff ID: ");
        String name = getInput("Enter name: ");
        String email = getInput("Enter email: ");
        
        Staff staff = new Staff(staffId, name, email);
        staffList.add(staff);
        System.out.println("Staff added successfully!");
    }

    private Schedule selectSchedule() {
        System.out.println("Available schedules:");
        for (int i = 0; i < schedules.size(); i++) {
            System.out.println((i + 1) + ". " + schedules.get(i).getPeriodId());
        }
        
        int choice = getIntInput("Select schedule: ") - 1;
        if (choice < 0 || choice >= schedules.size()) {
            System.out.println("Invalid selection.");
            return null;
        }
        
        return schedules.get(choice);
    }

    private Staff selectStaff() {
        System.out.println("Available staff:");
        for (int i = 0; i < staffList.size(); i++) {
            System.out.println((i + 1) + ". " + staffList.get(i));
        }
        
        int choice = getIntInput("Select staff: ") - 1;
        if (choice < 0 || choice >= staffList.size()) {
            System.out.println("Invalid selection.");
            return null;
        }
        
        return staffList.get(choice);
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private LocalDate getDateInput(String prompt) {
        while (true) {
            try {
                String input = getInput(prompt);
                return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (Exception e) {
                System.out.println("Invalid date format. Use yyyy-MM-dd");
            }
        }
    }

    private LocalTime getTimeInput(String prompt) {
        while (true) {
            try {
                String input = getInput(prompt);
                return LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (Exception e) {
                System.out.println("Invalid time format. Use HH:mm");
            }
        }
    }

    public static void main(String[] args) {
        StaffSchedulingSystem system = new StaffSchedulingSystem();
        system.run();
    }
}