package room_cleanliness_manager;

public class supply {
    
    private String supplyID;
    private String name;
    private int currentQuantity;
    private int minimumRequired;

    public supply(String supplyID, String name, int currentQuantity, int minimumRequired) {
        this.supplyID = supplyID;
        this.name = name;
        this.currentQuantity = currentQuantity;
        this.minimumRequired = minimumRequired;
    }

    public String getSupplyID() {
        return supplyID;
    }

    public String getName() {
        return name;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public int getMinimumRequired() {
        return minimumRequired;
    }

    public boolean needsRestock() {
        return currentQuantity < minimumRequired;
    }
}