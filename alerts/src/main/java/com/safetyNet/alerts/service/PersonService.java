package com.safetyNet.alerts.service;

import java.util.List;

import com.safetyNet.alerts.dto.request.ChildAlertDTO;
import com.safetyNet.alerts.dto.request.PersonInfoDTO;
import com.safetyNet.alerts.model.Person;

public interface PersonService {

	public void add(Person element);

	public void delete(Person element);

	public void update(Person element);

	public List<Person> findAll();

	public List<String> getCommunityMail(String city);

	public List<String> getPhoneByFireStation(String firestation);

	public List<ChildAlertDTO> getChildInfos(String address);

	public List<PersonInfoDTO> getMedicalRecordsByPerson(String firstName, String lastName);
}
