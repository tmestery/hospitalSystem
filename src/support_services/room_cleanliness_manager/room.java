package room_cleanliness_manager;

import java.time.LocalDateTime;

public class room {

    private String roomID;
    private String status;
    private bed bed;
    private LocalDateTime lastCleaned;

    public room(String roomID, String status, bed bed, LocalDateTime lastCleaned) {
        this.roomID = roomID;
        this.status = status;
        this.bed = bed;
        this.lastCleaned = lastCleaned;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getStatus() {
        return status;
    }

    public bed getBed() {
        return bed;
    }

    public LocalDateTime getLastCleaned() {
        return lastCleaned;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public void updateLastCleaned(LocalDateTime time) {
        this.lastCleaned = time;
    }
}