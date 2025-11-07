package iteration1;

import java.time.Instant;
import java.util.UUID;

public class TestRequest {
    private final UUID requestId;
    private final UUID patientId;
    private final UUID doctorId;
    private final UUID labDeptId;
    private final String testCode;
    private final Instant requestedAt;
    RequestStatus status;
    String notes;
    
    TestRequest(UUID reqId, UUID patId, UUID docId, UUID labId, String testCode) {
        this.requestId = reqId;
        this.patientId = patId;
        this.doctorId = docId;
        this.labDeptId = labId;
        this.testCode = testCode;
        this.requestedAt = Instant.now();
        this.status = RequestStatus.PENDING;
    }
    
    public UUID getRequestId()
    {
    	return requestId;
    }
    
    public UUID getPatientId()
    {
    	return patientId;
    }
    
    public UUID getDoctorId()
    {
    	return doctorId;
    }
    
    public UUID getLabDeptId()
    {
    	return labDeptId;
    }
    
    public String getTestCode()
    {
    	return testCode;
    }
    
    public Instant getRequestedAt()
    {
    	return requestedAt;
    }
    
    
    
	

}
