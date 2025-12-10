package diagnostic_services.iteration_2;

import java.util.ArrayList;
import roles.EMT;
import roles.Patient;

public class Ambulance {
  ArrayList<EMT> emt = new ArrayList<>(2);
  Patient patient;

  public Ambulance(EMT emt1, EMT emt2) {
    this.emt.add(emt1);
    this.emt.add(emt2);
  }

  public ArrayList<EMT> getEMT() {
    return emt;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }
}
