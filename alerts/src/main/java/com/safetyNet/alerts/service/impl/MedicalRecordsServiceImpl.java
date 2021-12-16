package com.safetyNet.alerts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.safetyNet.alerts.dto.request.ChildAlertDTO;
import com.safetyNet.alerts.dto.request.FireAlertDTO;
import com.safetyNet.alerts.dto.request.PersonInfoDTO;
import com.safetyNet.alerts.dto.request.fireStationInfo.FireStationInfosDTO;
import com.safetyNet.alerts.dto.request.floodAlert.FloodAlertDTO;
import com.safetyNet.alerts.model.MedicalRecords;
import com.safetyNet.alerts.repository.MedicalRecordsRepository;
import com.safetyNet.alerts.service.MedicalRecordsService;

@Service
public class MedicalRecordsServiceImpl implements MedicalRecordsService {
	@Autowired
	@Qualifier("medicalRecordsRepoSingleton")
	MedicalRecordsRepository medicalRecordsRepository;

	@Override
	public void add(MedicalRecords medicalRecord) {
		medicalRecordsRepository.add(medicalRecord);
	}

	@Override
	public void delete(MedicalRecords element) {
		medicalRecordsRepository.delete(element);
	}

	@Override
	public void update(MedicalRecords element) {
		medicalRecordsRepository.update(element);
	}

	@Override
	public List<MedicalRecords> findAll() {
		return medicalRecordsRepository.findAll();
	}

	@Override
	public void addAll(List<MedicalRecords> elements) {
		medicalRecordsRepository.addAll(elements);
	}

	@Override
	public List<ChildAlertDTO> fillChildInfos(List<ChildAlertDTO> childFamily) {
		return medicalRecordsRepository.fillChildInfos(childFamily);
	}

	@Override
	public List<PersonInfoDTO> getMedicalRecordsByPerson(String firstName, String lastName) {
		return medicalRecordsRepository.getMedicalRecordsByPerson(firstName, lastName);
	}

	@Override
	public FireStationInfosDTO countChildAdult(FireStationInfosDTO fireStationInfos) {
		return medicalRecordsRepository.countChildAdult(fireStationInfos);
	}

	@Override
	public List<FloodAlertDTO> fillFloodAlertDTO(List<FloodAlertDTO> personByAdressList) {
		return medicalRecordsRepository.fillFloodAlertDTO(personByAdressList);
	}

	@Override
	public FireAlertDTO fillMedicalRecordsByPerson(FireAlertDTO infoPerson) {
		return medicalRecordsRepository.fillMedicalRecordsByPerson(infoPerson);
	}

}
