package food_nutrition_manager;

public class MealOrder {
    private int orderId;
    private String orderDate;
    private String mealType;
    private String status;
    private String specialNotes;

    private Patient patient;
    private Meal meal;
    private Kitchen kitchen;
    private DeliveryStaff deliveryStaff;
    private DeliveryConfirmation confirmation;

    public MealOrder(int orderId, String orderDate, String mealType) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.mealType = mealType;
        this.status = "Created";
    }

    public void assignDeliveryStaff(DeliveryStaff staff) {
        this.deliveryStaff = staff;
    }

    public void confirmDelivery(DeliveryConfirmation confirmation) {
        this.confirmation = confirmation;
        this.status = "Confirmed";
    }

    // getters and setters
    public int getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }
}