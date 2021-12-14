package com.safetyNet.alerts.service;

import java.util.List;

import com.safetyNet.alerts.dto.request.ChildAlertDTO;
import com.safetyNet.alerts.dto.request.PersonInfoDTO;
import com.safetyNet.alerts.model.MedicalRecords;

public interface MedicalRecordsService extends CrudService<MedicalRecords> {

	List<PersonInfoDTO> getMedicalRecordsByPerson(String firstName, String lastName);

	List<ChildAlertDTO> fillChildInfos(List<ChildAlertDTO> childFamily);
}
