package com.safetyNet.alerts.repository;

import java.util.List;

import com.safetyNet.alerts.dto.request.ChildAlertDTO;
import com.safetyNet.alerts.dto.request.FireAlertDTO;
import com.safetyNet.alerts.dto.request.PersonInfoDTO;
import com.safetyNet.alerts.dto.request.fireStationInfo.FireStationInfosDTO;
import com.safetyNet.alerts.dto.request.floodAlert.FloodAlertDTO;
import com.safetyNet.alerts.model.MedicalRecords;

public interface MedicalRecordsRepository extends CrudRepository<MedicalRecords> {

	List<PersonInfoDTO> getMedicalRecordsByPerson(String firstName, String lastName);

	List<ChildAlertDTO> fillChildInfos(List<ChildAlertDTO> childFamily);

	FireStationInfosDTO countChildAdult(FireStationInfosDTO fireStationInfos);

	List<FloodAlertDTO> fillFloodAlertDTO(List<FloodAlertDTO> personByAdressList);

	FireAlertDTO fillMedicalRecordsByPerson(FireAlertDTO infoPerson);

}
