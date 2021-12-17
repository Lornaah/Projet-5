package com.safetyNet.alerts.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MedicalRecords {

	private String firstName;

	private String lastName;

	private String birthdate;

	private final List<String> medications = new ArrayList<>();

	private final List<String> allergies = new ArrayList<>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public List<String> getMedications() {
		return Collections.unmodifiableList(medications);
	}

	public void setMedications(List<String> medications) {
		this.medications.clear();
		this.medications.addAll(medications);
	}

	public List<String> getAllergies() {
		return Collections.unmodifiableList(allergies);
	}

	public void setAllergies(List<String> allergies) {
		this.allergies.clear();
		this.allergies.addAll(allergies);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicalRecords other = (MedicalRecords) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "MedicalRecords [firstName=" + firstName + ", lastName=" + lastName + ", birthdate=" + birthdate
				+ ", medications=" + medications + ", allergies=" + allergies + "]";
	}

}
