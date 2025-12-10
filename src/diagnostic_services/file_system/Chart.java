package diagnostic_services.file_system;

import java.util.ArrayList;
import roles.*;

public class Chart {
	private Patient patient;
	private Integer chartId;
	private HealthCareProvider user;
	private String date = "Dec 10, 2025";
	private final ArrayList<HealthCareProvider> others = new ArrayList<>();
	private String allergies;
	private String notes;

	public Chart() {}

	public Chart(HealthCareProvider user, Patient patient, String notes) {
		this.user = user;
		this.patient = patient;
		this.others.addAll(others);
		this.notes = notes;
	}

	public HealthCareProvider getUser() {
		return user;
	}

	public Patient getPatient() {
		return patient;
	}

	public void addNote(String str) {
		notes = str;
	}

	public String getNotes() {
		return notes;
	}

	public ArrayList<HealthCareProvider> getOthers() {
		return others;
	}

	public String getAllergies() {
		return allergies;
	}

	public void addAllergies(String all) {
		allergies = all;
	}

	public void addOthers(HealthCareProvider other) {
		others.add(other);
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setUser(HealthCareProvider person) {
		this.user = person;
	}

	public void setChartId(int id) {
		this.chartId = id;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public int getId() {
		return this.chartId;
	}

	@Override
	public String toString() {
		return " Chart Id: " + chartId + "\n" +
				" Patient: " + patient + "\n" +
				" Issued by: " + user + "\n" +
				" Date Issued: " + date + "\n" +
				" Others: " + others + "\n" +
				" Allergies: " + allergies + "\n" +
				" Notes: " + notes + "\n" +
				"--------------------------------------------------------------------------- \n";
	}
}
