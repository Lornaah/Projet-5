package com.safetyNet.alerts.dto.request.fireStationInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FireStationInfosDTO {

	private String stationNumber;

	private final List<PersonByStationNumber> infoPerson = new ArrayList<>();

	private int nbAdults;

	private int nbChild;

	public String getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(String stationNumber) {
		this.stationNumber = stationNumber;
	}

	public List<PersonByStationNumber> getInfoPerson() {
		return Collections.unmodifiableList(infoPerson);
	}

	public void setInfoPerson(List<PersonByStationNumber> infoPerson) {
		this.infoPerson.clear();
		this.infoPerson.addAll(infoPerson);
	}

	public int getNbAdults() {
		return nbAdults;
	}

	public void setNbAdults(int nbAdults) {
		this.nbAdults = nbAdults;
	}

	public int getNbChild() {
		return nbChild;
	}

	public void setNbChild(int nbChild) {
		this.nbChild = nbChild;
	}

	public void addPerson(PersonByStationNumber person) {
		infoPerson.add(person);
	}

	@Override
	public String toString() {
		return "FireStationInfosDTO [stationNumber=" + stationNumber + ", infoPerson=" + infoPerson + ", nbAdults="
				+ nbAdults + ", nbChild=" + nbChild + "]";
	}

}
