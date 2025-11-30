package room_cleanliness_manager;

public class bed {

    private String bedID;
    private String status;

    public bed(String bedID, String status) {
        this.bedID = bedID;
        this.status = status;
    }

    public String getBedID() {
        return bedID;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}