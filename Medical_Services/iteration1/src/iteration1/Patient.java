package iteration1;

import java.time.LocalDate;

import java.util.UUID;

public class Patient {
	 final UUID patientID;
	 final String fullName;
	 final LocalDate dateOfBirth;
	
	Patient(UUID patientID, String fullName, LocalDate dateOfBirth)
	{
		this.patientID = patientID;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
	}
	
	public UUID getPatientID()
	{
		return patientID;
	}
	public String getFullName()
	{
		return fullName;
	}
	public LocalDate getDateOfBirth()
	{
		return dateOfBirth;
	}
	@Override public String toString() 
	{
		return fullName + " ( DOB:" + dateOfBirth + ") ";
	}

}
