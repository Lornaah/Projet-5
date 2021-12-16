package com.safetyNet.alerts.dto.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonInfoDTO {

	private String firstName;

	private String lastName;

	private String address;

	private int age;

	private String email;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getMedications() {
		return Collections.unmodifiableList(medications);
	}

	public List<String> getAllergies() {
		return Collections.unmodifiableList(allergies);
	}

	public void setAllergies(List<String> allergies) {
		this.allergies.clear();
		this.allergies.addAll(allergies);
	}

	public void setMedications(List<String> medications) {
		this.medications.clear();
		this.medications.addAll(medications);
	}

}
