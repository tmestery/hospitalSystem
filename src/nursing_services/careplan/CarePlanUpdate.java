package nursing_services.careplan;

public class CarePlanUpdate {

    private String patientId;
    private String patientName;
    private String nurseId;
    private String nurseName;
    private String type;
    private String description;
    private String date;
    private String time;

    public CarePlanUpdate(String patientId, String patientName, String nurseId,
                          String nurseName, String type, String description,
                          String date, String time) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.nurseId = nurseId;
        this.nurseName = nurseName;
        this.type = type;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public CarePlanUpdate(String patientId, String patientName, String nurseId,
                          String nurseName, String type, String description) {
        this(patientId, patientName, nurseId, nurseName, type, description, "", "");
    }

    public String toCSV() {
        return patientId + "," + patientName + "," + nurseId + "," + nurseName + "," +
                type + ",\"" + description + "\"," + date + "," + time;
    }

    public String getType() { return type; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getTime() { return time; }
}
