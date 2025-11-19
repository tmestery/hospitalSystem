
import java.util.ArrayList;
import java.util.List;

public class ConflictCheck {
    private boolean hasOverlap;
    private boolean exceedsHourLimit;
    private List<String> errors;

    public ConflictCheck() {
        this.hasOverlap = false;
        this.exceedsHourLimit = false;
        this.errors = new ArrayList<>();
    }

    public boolean hasOverlap() { return hasOverlap; }
    public boolean exceedsHourLimit() { return exceedsHourLimit; }
    public List<String> getErrors() { return errors; }
    
    public void setHasOverlap(boolean hasOverlap) { this.hasOverlap = hasOverlap; }
    public void setExceedsHourLimit(boolean exceedsHourLimit) { this.exceedsHourLimit = exceedsHourLimit; }
    public void addError(String error) { this.errors.add(error); }
    
    public boolean isValid() {
        return !hasOverlap && !exceedsHourLimit && errors.isEmpty();
    }
}