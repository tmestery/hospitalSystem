package nursing_services.careplan;

import java.time.LocalDateTime;

public class CarePlanEntry {

    private String category;        
    private String description;     
    private int progressLevel;      
    private LocalDateTime timestamp;

    public CarePlanEntry(String category, String description) {
        this.category = category;
        this.description = description;
        this.progressLevel = 0; 
        this.timestamp = LocalDateTime.now();
    }

    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public int getProgressLevel() { return progressLevel; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setProgressLevel(int level) {
        this.progressLevel = level;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] Category: " + category +
               " | Description: " + description +
               " | Progress: " + progressText();
    }

    private String progressText() {
        return switch(progressLevel) {
            case 0 -> "Not Started yet";
            case 1 -> "In Progress";
            case 2 -> "Partially Achieved";
            case 3 -> "Achieved";
            case 4 -> "Needs more Revision";
            default -> "Unknown";
        };
    }
}
