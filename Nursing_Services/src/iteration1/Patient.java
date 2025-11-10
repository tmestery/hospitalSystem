package iteration1;

public class Patient {
	  final String patientID;
	    final String name;
	    final String roomNumber;

	    public Patient(String patientID, String name, String roomNumber) {
	        this.patientID = patientID;
	        this.name = name;
	        this.roomNumber = roomNumber;
	    }

	    @Override
	    public String toString() {
	        return name + " (Room: " + roomNumber + ")";
	    }
}
