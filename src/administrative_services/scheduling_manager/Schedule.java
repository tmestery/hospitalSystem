
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Schedule {

    private String periodId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDateTime publishedAt;
    private List<Shift> shifts;
    private SchedulingUser manager;

    public Schedule(String periodId, LocalDate startDate, LocalDate endDate, SchedulingUser manager) {
        this.periodId = periodId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "DRAFT";
        this.shifts = new ArrayList<>();
        this.manager = manager;
    }

    public String getPeriodId() {
        return periodId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void addShift(Shift shift) {
        shifts.add(shift);
    }

    public void publish() {
        this.status = "PUBLISHED";
        this.publishedAt = LocalDateTime.now();
    }

    public ConflictCheck validateShift(Shift newShift, Staff staff) {
        ConflictCheck check = new ConflictCheck();

        // Check for overlapping shifts
        for (Shift existing : shifts) {
            if (existing.getAssignedStaff() != null
                    && existing.getAssignedStaff().getStaffId().equals(staff.getStaffId())
                    && existing.getDate().equals(newShift.getDate())) {

                if (shiftsOverlap(existing, newShift)) {
                    check.setHasOverlap(true);
                    check.addError("Shift overlaps with existing shift: " + existing.getShiftId());
                }
            }
        }

        // Check hour limits (max 40 hours per week)
        int weekHours = calculateWeekHours(staff, newShift.getDate());
        int shiftHours = (int) Duration.between(newShift.getStartTime(), newShift.getEndTime()).toHours();

        if (weekHours + shiftHours > 40) {
            check.setExceedsHourLimit(true);
            check.addError("Assigning this shift would exceed 40-hour weekly limit");
        }

        return check;
    }

    private boolean shiftsOverlap(Shift s1, Shift s2) {
        return s1.getStartTime().isBefore(s2.getEndTime())
                && s2.getStartTime().isBefore(s1.getEndTime());
    }

    private int calculateWeekHours(Staff staff, LocalDate date) {
        LocalDate weekStart = date.with(DayOfWeek.MONDAY);
        LocalDate weekEnd = weekStart.plusDays(6);

        return shifts.stream()
                .filter(s -> s.getAssignedStaff() != null)
                .filter(s -> s.getAssignedStaff().getStaffId().equals(staff.getStaffId()))
                .filter(s -> !s.getDate().isBefore(weekStart) && !s.getDate().isAfter(weekEnd))
                .mapToInt((var s) -> (int) Duration.between(s.getStartTime(), s.getEndTime()).toHours())
                .sum();
    }

    public CoverageReport generateCoverageReport(LocalDate date) {
        CoverageReport report = new CoverageReport(date);

        List<Shift> dayShifts = shifts.stream()
                .filter(s -> s.getDate().equals(date))
                .collect(Collectors.toList());

        for (Shift shift : dayShifts) {
            int hours = (int) Duration.between(shift.getStartTime(), shift.getEndTime()).toHours();
            report.addHours(shift.getRole(), hours);

            if (shift.getAssignedStaff() == null) {
                report.addGap(String.format("%s shift at %s (%s-%s) - UNASSIGNED",
                        shift.getRole(), shift.getLocation(), shift.getStartTime(), shift.getEndTime()));
            }
        }

        return report;
    }
}
