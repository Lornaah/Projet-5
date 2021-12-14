package com.safetyNet.alerts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.safetyNet.alerts.dto.request.ChildAlertDTO;
import com.safetyNet.alerts.dto.request.PersonInfoDTO;
import com.safetyNet.alerts.model.Person;
import com.safetyNet.alerts.repository.PersonRepository;
import com.safetyNet.alerts.service.FireStationService;
import com.safetyNet.alerts.service.MedicalRecordsService;
import com.safetyNet.alerts.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	@Qualifier("personRepoSingleton")
	PersonRepository personRepository;

	@Autowired
	FireStationService fireStationService;

	@Autowired
	MedicalRecordsService medicalRecordsService;

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public void add(Person element) {
		personRepository.add(element);
	}

	@Override
	public void delete(Person element) {
		personRepository.delete(element);
	}

	@Override
	public void update(Person element) {
		personRepository.update(element);
	}

	@Override
	public List<String> getCommunityMail(String city) {
		return personRepository.getCommunityMail(city);
	}

	@Override
	public List<String> getPhoneByFireStation(String firestationNum) {
		List<String> addressByFireStation = fireStationService.getAddressByFireStation(firestationNum);
		return personRepository.getPhoneByFireStation(addressByFireStation);
	}

	@Override
	public List<PersonInfoDTO> getMedicalRecordsByPerson(String firstName, String lastName) {
		List<PersonInfoDTO> medicalRecordsByPersonDTO = medicalRecordsService.getMedicalRecordsByPerson(firstName,
				lastName);
		return personRepository.fillMedicalRecordsByPersonDTO(medicalRecordsByPersonDTO);
	}

	@Override
	public List<ChildAlertDTO> getChildInfos(String address) {
		List<ChildAlertDTO> childFamily = personRepository.getFamilyInfos(address);
		return medicalRecordsService.fillChildInfos(childFamily);

	}
}
