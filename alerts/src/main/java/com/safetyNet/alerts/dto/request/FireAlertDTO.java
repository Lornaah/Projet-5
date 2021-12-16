package com.safetyNet.alerts.dto.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.safetyNet.alerts.dto.request.floodAlert.FloodAlertPerson;

public class FireAlertDTO {

	private String address;

	private String stationNum;

	private final List<FloodAlertPerson> infoPerson = new ArrayList<>();

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStationNum() {
		return stationNum;
	}

	public void setStationNum(String stationNum) {
		this.stationNum = stationNum;
	}

	public List<FloodAlertPerson> getListPerson() {
		return Collections.unmodifiableList(infoPerson);
	}

	public void setListPerson(List<FloodAlertPerson> infoPerson) {
		getListPerson().clear();
		getListPerson().addAll(infoPerson);
	}

	public void addPerson(FloodAlertPerson person) {
		infoPerson.add(person);
	}
}
