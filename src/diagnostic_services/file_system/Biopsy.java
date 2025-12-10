package diagnostic_services.file_system;

import roles.*;

public class Biopsy {
  private final Category type;
  private final Doctor doctor;
  private final Patient patient;
  private Status status;
  private String notes = "";
  private int id = 0;

  public Biopsy(Category type, Doctor doctor, Patient patient, Status status, int id) {
    this.type = type;
    this.doctor = doctor;
    this.patient = patient;
    this.status = status;
    this.id = id;
  }

  public Biopsy(Category type, Doctor doctor, Patient patient) {
    this.type = type;
    this.doctor = doctor;
    this.patient = patient;
    this.status = Status.PENDING;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public Status getStatus() {
    return status;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public void addNotes(String notes) {
    if (this.notes.isEmpty()) {
      this.notes = notes;
    } else {
      this.notes += " " + notes;
    }
  }

  @Override
  public String toString() {
    return "Biopsy ID: " + id + "\n" +
            "Type: " + type + "\n" +
            "Doctor: " + doctor + "\n" +
            "Patient: " + patient + "\n"
            + "Status: " + status + "\n"
            + "Notes: " + notes + "\n";
  }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Category getType() {
        return type;
    }

  public static enum Category {
    NEEDLE,
    SURGICAL,
    ENDOSCOPIC,
    DERMATOLOGIC
  }
}
