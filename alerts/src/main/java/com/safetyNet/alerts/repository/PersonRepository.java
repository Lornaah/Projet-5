package com.safetyNet.alerts.repository;

import java.util.List;

import com.safetyNet.alerts.dto.request.ChildAlertDTO;
import com.safetyNet.alerts.dto.request.FireAlertDTO;
import com.safetyNet.alerts.dto.request.PersonInfoDTO;
import com.safetyNet.alerts.dto.request.fireStationInfo.FireStationInfosDTO;
import com.safetyNet.alerts.dto.request.floodAlert.FloodAlertDTO;
import com.safetyNet.alerts.model.Person;

public interface PersonRepository extends CrudRepository<Person> {

	List<String> getCommunityMail(String city);

	List<String> getPhoneByFireStation(List<String> addressByFireStation);

	List<PersonInfoDTO> fillMedicalRecordsByPersonDTO(List<PersonInfoDTO> medicalRecordsByPersonDTO);

	List<ChildAlertDTO> getFamilyInfos(String address);

	FireStationInfosDTO getFireStationInfos(String stationNumber, List<String> addresses);

	List<FloodAlertDTO> getPersonByAddress(List<FloodAlertDTO> addressList);

	FireAlertDTO fillFirePersonInfo(FireAlertDTO firePersonInfo);

}
