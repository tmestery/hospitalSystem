package iteration1;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MedicalSystem {
	
	public static final String TestType = null;
	final Map<UUID, Doctor> doctors = new HashMap<>();
    final Map<UUID, Patient> patients = new LinkedHashMap<>();
    final Map<String, TestType> testTypes = new LinkedHashMap<>();
    LabDept labDept = new LabDept(UUID.randomUUID(), "Lab");
    final List<TestRequest> requests = new ArrayList<>();
    boolean simulateNotifyFailure = false;  //that is if you click in main that you want to simulate notify failure

   

    public Patient selectPatient(UUID doctorId, UUID patientId) {
        Patient p = patients.get(patientId);
        if (p == null) {
            notifyDoctorError(doctorId, "Patients not found");
            throw new IllegalArgumentException("Patients not found: " + patientId);
        }
        System.out.println("Loaded patient: " + p.fullName + " (" + p.patientID + ")" + "Born on " + p.dateOfBirth);
        return p;
    }
    
    public UUID requestDiagnosticTest(UUID doctorID, UUID patientID, String testCode){
    	if(!doctors.containsKey(doctorID))
    		throw new IllegalArgumentException("Doctor not found");
    	if(!patients.containsKey(patientID))
    		throw new IllegalArgumentException("patient not found");
    	if(!testTypes.containsKey(testCode))
    		throw new IllegalArgumentException("test type not found");
    	
    	UUID requestID = UUID.randomUUID();
    	 TestRequest req = new TestRequest(requestID, patientID, doctorID, labDept.labID, testCode);
         requests.add(req);
         boolean sent = notifyLab(labDept.labID, requestID);
         if (sent)
         {
        	 req.status = RequestStatus.SENT_TO_LAB;
        	 notifyDoctorConfirmation(doctorID, requestID);  	 
         }
         else 
         {
        	 notifyDoctorError(doctorID, "Request did not send");
        	 req.status = RequestStatus.PENDING;
        	 req.notes = "Notify to lab failed at " + Instant.now();
        	 
         }
         return requestID;



    	
    	
    }
    
    private void notifyDoctorConfirmation(UUID doctorId, UUID requestId) 
    {
        System.out.println("[Doctor " + doctorId + "] Confirmation: request " + requestId + " created & sent.");
    }
    
    private void notifyDoctorError(UUID doctorId, String message) {
        System.out.println("[Doctor " + doctorId + "] ERROR: " + message);
    }
    
    private boolean notifyLab(UUID labDeptID, UUID requestID)
    {
        if (simulateNotifyFailure) {
            System.out.println("[LabDept " + labDept.labID + "] FAILED to receive request " + requestID);
            return false;
        }
    	  System.out.println("[LabDept " + labDeptID + "] Received request " + requestID);
          return true;
  
    }


}
