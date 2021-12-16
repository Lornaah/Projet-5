package com.safetyNet.alerts.dto.request.floodAlert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FloodAlertPerson {

	private String firstName;

	private String lastName;

	private final List<String> medications = new ArrayList<>();

	private final List<String> allergies = new ArrayList<>();

	private String phoneNumber;

	private int age;

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

}
