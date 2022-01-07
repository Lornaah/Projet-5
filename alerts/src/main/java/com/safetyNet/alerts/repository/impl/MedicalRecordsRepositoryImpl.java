package com.safetyNet.alerts.repository.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.safetyNet.alerts.dto.request.ChildAlertDTO;
import com.safetyNet.alerts.dto.request.FireAlertDTO;
import com.safetyNet.alerts.dto.request.PersonInfoDTO;
import com.safetyNet.alerts.dto.request.fireStationInfo.FireStationInfosDTO;
import com.safetyNet.alerts.dto.request.fireStationInfo.PersonByStationNumber;
import com.safetyNet.alerts.dto.request.floodAlert.FloodAlertDTO;
import com.safetyNet.alerts.dto.request.floodAlert.FloodAlertPerson;
import com.safetyNet.alerts.model.MedicalRecords;
import com.safetyNet.alerts.repository.MedicalRecordsRepository;

@Repository
public class MedicalRecordsRepositoryImpl implements MedicalRecordsRepository {

	private static Logger logger = LogManager.getLogger(MedicalRecordsRepositoryImpl.class);

	private final List<MedicalRecords> list = new ArrayList<>();

	@Bean
	@Scope("singleton")
	public MedicalRecordsRepositoryImpl medicalRecordsRepoSingleton() {
		return new MedicalRecordsRepositoryImpl();
	}

	@Override
	public void add(MedicalRecords element) {
		list.add(element);
	}

	@Override
	public void delete(MedicalRecords element) {
		list.remove(element);
	}

	@Override
	public void update(MedicalRecords element) {
		list.forEach(m -> {
			if (m.equals(element)) {
				m.setBirthdate(element.getBirthdate());
				m.setMedications(element.getMedications());
				m.setAllergies(element.getAllergies());
			}
		});
	}

	@Override
	public List<MedicalRecords> findAll() {
		return Collections.unmodifiableList(list);
	}

	@Override
	public void addAll(List<MedicalRecords> elements) {
		list.addAll(elements);

	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public List<PersonInfoDTO> getMedicalRecordsByPerson(String firstName, String lastName) {
		List<PersonInfoDTO> medicalRecordsByPerson = new ArrayList<>();
		list.forEach(m -> {
			if (m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)) {
				PersonInfoDTO medicalRecordsByPersonDTO = new PersonInfoDTO();

				medicalRecordsByPersonDTO.setAllergies(m.getAllergies());
				medicalRecordsByPersonDTO.setMedications(m.getMedications());
				medicalRecordsByPersonDTO.setAge(MedicalRecordsRepositoryImpl.getAge(m));
				medicalRecordsByPersonDTO.setFirstName(m.getFirstName());
				medicalRecordsByPersonDTO.setLastName(m.getLastName());

				medicalRecordsByPerson.add(medicalRecordsByPersonDTO);
			}
		});
		return medicalRecordsByPerson;
	}

	@Override
	public List<ChildAlertDTO> fillChildInfos(List<ChildAlertDTO> childFamily) {
		List<ChildAlertDTO> childInfos = new ArrayList<>();

		list.forEach(m -> {
			for (ChildAlertDTO c : childFamily) {
				if (c.getFirstName().equals(m.getFirstName()) && c.getLastName().equals(m.getLastName())) {
					int age = (MedicalRecordsRepositoryImpl.getAge(m));
					if (age < 18) {
						c.setAge(age);
						childInfos.add(c);
					}
				}
			}
		});
		return childInfos;
	}

	private static int getAge(MedicalRecords medicalRecords) {

		String birthdateAsString = (medicalRecords.getBirthdate());
		LocalDate birthdate = LocalDate.parse(birthdateAsString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		int age = Period.between(birthdate, LocalDate.now()).getYears();

		logger.debug("Getting difference between" + LocalDate.now().toString() + " and " + birthdate.toString());

		return age;
	}

	@Override
	public FireStationInfosDTO countChildAdult(FireStationInfosDTO fireStationInfos) {
		list.forEach(m -> {
			for (PersonByStationNumber p : fireStationInfos.getInfoPerson()) {

				logger.debug("determining if " + p.toString() + " is the same person than " + m.toString());

				if (p.getFirstName().equals(m.getFirstName()) && p.getLastName().equals(m.getLastName())) {
					int age = MedicalRecordsRepositoryImpl.getAge(m);
					if (age < 18) {
						fireStationInfos.setNbChild(fireStationInfos.getNbChild() + 1);
					} else {
						fireStationInfos.setNbAdults(fireStationInfos.getNbAdults() + 1);
					}
				}
			}
		});
		return fireStationInfos;
	}

	@Override
	public List<FloodAlertDTO> fillFloodAlertDTO(List<FloodAlertDTO> personByAdressList) {
		personByAdressList.forEach(dto -> {
			dto.getAddressList().forEach(a -> {
				a.getListPerson().forEach(p -> fillAlertPersonList(p));
			});
		});
		return personByAdressList;
	}

	private void fillAlertPersonList(FloodAlertPerson p) {
		list.forEach(m -> {

			logger.debug("determining if " + p.toString() + " is the same person than " + m.toString());

			if (m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())) {
				p.setAge(getAge(m));
				p.setAllergies(m.getAllergies());
				p.setMedications(m.getMedications());
			}
		});
	}

	@Override
	public FireAlertDTO fillMedicalRecordsByPerson(FireAlertDTO infoPerson) {
		infoPerson.getListPerson().forEach(p -> fillAlertPersonList(p));
		return infoPerson;
	}
}
