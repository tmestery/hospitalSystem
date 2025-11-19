
import java.time.LocalDate;
import java.time.LocalTime;

public class Shift {
    private String shiftId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String role;
    private String location;
    private Staff assignedStaff;

    public Shift(String shiftId, LocalDate date, LocalTime startTime, LocalTime endTime, String role, String location) {
        this.shiftId = shiftId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.role = role;
        this.location = location;
    }

    public String getShiftId() { return shiftId; }
    public LocalDate getDate() { return date; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public String getRole() { return role; }
    public String getLocation() { return location; }
    public Staff getAssignedStaff() { return assignedStaff; }
    
    public void assignStaff(Staff staff) { this.assignedStaff = staff; }

    @Override
    public String toString() {
        String assignment = assignedStaff != null ? assignedStaff.getName() : "UNASSIGNED";
        return String.format("Shift[%s: %s %s-%s, %s @ %s, Staff: %s]", 
            shiftId, date, startTime, endTime, role, location, assignment);
    }
}