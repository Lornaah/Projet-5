package com.safetyNet.alerts.repository;

import java.util.List;

import com.safetyNet.alerts.dto.request.ChildAlertDTO;
import com.safetyNet.alerts.dto.request.PersonInfoDTO;
import com.safetyNet.alerts.model.Person;

public interface PersonRepository extends CrudRepository<Person> {

	List<String> getCommunityMail(String city);

	List<String> getPhoneByFireStation(List<String> addressByFireStation);

	List<PersonInfoDTO> fillMedicalRecordsByPersonDTO(List<PersonInfoDTO> medicalRecordsByPersonDTO);

	List<ChildAlertDTO> getFamilyInfos(String address);

}
