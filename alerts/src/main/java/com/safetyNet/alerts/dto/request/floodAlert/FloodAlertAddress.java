package com.safetyNet.alerts.dto.request.floodAlert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FloodAlertAddress {

	private String address;

	private final List<FloodAlertPerson> listPerson = new ArrayList<>();

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<FloodAlertPerson> getListPerson() {
		return Collections.unmodifiableList(listPerson);
	}

	public void setListPerson(List<FloodAlertPerson> listPerson) {
		getListPerson().clear();
		getListPerson().addAll(listPerson);
	}

	public void addPerson(FloodAlertPerson person) {
		listPerson.add(person);
	}

	@Override
	public String toString() {
		return "FloodAlertAddress [address=" + address + ", listPerson=" + listPerson + "]";
	}

}
