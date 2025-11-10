package support_services.equipment_inventory_manager;
import java.util.List;

public class inventory {

    private List<part> parts;

    public inventory(List<part> parts) {
        this.parts = parts;
    }

    // Getter:
    public List<part> getParts() {
        return parts;
    }

    // Setter:
    public void setParts(List<part> parts) {
        this.parts = parts;
    }

    // Helper methods:
    public void addPart(part p) {
        parts.add(p);
    }

    public void removePart(part p) {
        parts.remove(p);
    }
}