package com.safetyNet.alerts.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.safetyNet.alerts.model.FireStation;
import com.safetyNet.alerts.model.MedicalRecords;
import com.safetyNet.alerts.model.Person;

public class JsonListDTO {

	private final List<Person> listPersons = new ArrayList<>();
	private final List<FireStation> listFireStations = new ArrayList<>();
	private final List<MedicalRecords> listMedicalRecords = new ArrayList<>();

	public JsonListDTO() {
	}

	public JsonListDTO(List<Person> listPersons, List<FireStation> listFireStations,
			List<MedicalRecords> listMedicalRecords) {
		super();
		this.listPersons.addAll(listPersons);
		this.listFireStations.addAll(listFireStations);
		this.listMedicalRecords.addAll(listMedicalRecords);

	}

	public List<Person> getListPersons() {
		return Collections.unmodifiableList(listPersons);
	}

	public List<FireStation> getListFireStations() {
		return Collections.unmodifiableList(listFireStations);
	}

	public List<MedicalRecords> getListMedicalRecords() {
		return Collections.unmodifiableList(listMedicalRecords);
	}

}
