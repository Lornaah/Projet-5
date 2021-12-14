package com.safetyNet.alerts.repository;

import java.util.List;

import com.safetyNet.alerts.dto.MedicalRecordsByPersonDTO;
import com.safetyNet.alerts.model.MedicalRecords;

public interface MedicalRecordsRepository extends CrudRepository<MedicalRecords> {

	List<MedicalRecordsByPersonDTO> getMedicalRecordsByPerson(String firstName, String lastName);

}
