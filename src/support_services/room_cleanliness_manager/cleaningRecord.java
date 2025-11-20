package room_cleanliness_manager;

public class cleaningRecord {

    private int staffID;
    private String name;
    private String assignedZone;

    public cleaningRecord(int staffID, String name, String assignedZone) {
        this.staffID = staffID;
        this.name = name;
        this.assignedZone = assignedZone;
    }

    public int getStaffID() {
        return staffID;
    }

    public String getName() {
        return name;
    }

    public String getAssignedZone() {
        return assignedZone;
    }

    public room checkRoomAssignment() {
        return null;
    }
}