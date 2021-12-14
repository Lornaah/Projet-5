package com.safetyNet.alerts.repository;

import java.util.List;

import com.safetyNet.alerts.dto.MedicalRecordsByPersonDTO;
import com.safetyNet.alerts.model.Person;

public interface PersonRepository extends CrudRepository<Person> {

	List<String> getCommunityMail(String city);

	List<String> getPhoneByFireStation(List<String> addressByFireStation);

	List<MedicalRecordsByPersonDTO> fillMedicalRecordsByPersonDTO(
			List<MedicalRecordsByPersonDTO> medicalRecordsByPersonDTO);

}
