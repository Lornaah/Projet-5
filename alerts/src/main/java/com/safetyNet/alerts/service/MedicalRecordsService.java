package com.safetyNet.alerts.service;

import java.util.List;

import com.safetyNet.alerts.dto.MedicalRecordsByPersonDTO;
import com.safetyNet.alerts.model.MedicalRecords;

public interface MedicalRecordsService extends CrudService<MedicalRecords> {

	List<MedicalRecordsByPersonDTO> getMedicalRecordsByPerson(String firstName, String lastName);
}
