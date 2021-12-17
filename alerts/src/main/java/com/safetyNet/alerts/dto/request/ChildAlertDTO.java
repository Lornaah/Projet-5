package com.safetyNet.alerts.dto.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.safetyNet.alerts.model.Person;

public class ChildAlertDTO {

	private String firstName;

	private String lastName;

	private int age;

	private final List<Person> family = new ArrayList<>();

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Person> getFamily() {
		return Collections.unmodifiableList(family);
	}

	public void setFamily(List<Person> family) {
		this.family.clear();
		this.family.addAll(family);
	}

	@Override
	public String toString() {
		return "ChildAlertDTO [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", family="
				+ family + "]";
	}

}
