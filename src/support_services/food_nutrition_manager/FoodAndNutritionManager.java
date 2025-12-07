package food_nutrition_manager;

public class FoodAndNutritionManager {
    private int managerId;
    private String name;
    private String role;

    public FoodAndNutritionManager(int managerId, String name, String role) {
        this.managerId = managerId;
        this.name = name;
        this.role = role;
    }

    // getters and setters
    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}