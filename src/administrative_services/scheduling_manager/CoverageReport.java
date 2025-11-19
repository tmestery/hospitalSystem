
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class CoverageReport {

    private LocalDate date;
    private Map<String, Integer> hoursByRole;
    private List<String> gaps;

    public CoverageReport(LocalDate date) {
        this.date = date;
        this.hoursByRole = new HashMap<>();
        this.gaps = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public Map<String, Integer> getHoursByRole() {
        return hoursByRole;
    }

    public List<String> getGaps() {
        return gaps;
    }

    public void addHours(String role, int hours) {
        hoursByRole.put(role, hoursByRole.getOrDefault(role, 0) + hours);
    }

    public void addGap(String gap) {
        gaps.add(gap);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Coverage Report for ").append(date).append("\n");
        sb.append("Hours by Role:\n");
        hoursByRole.forEach((role, hours)
                -> sb.append("  ").append(role).append(": ").append(hours).append(" hours\n"));
        sb.append("Gaps:\n");
        if (gaps.isEmpty()) {
            sb.append("  None\n");
        } else {
            gaps.forEach(gap -> sb.append("  - ").append(gap).append("\n"));
        }
        return sb.toString();
    }
}
