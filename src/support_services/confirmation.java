package support_services;
import java.time.LocalDateTime;

public interface confirmation {
    String getEquipmentID();
    LocalDateTime getTimestamp();
    String getMessage();
}