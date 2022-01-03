package com.safetyNet.alerts.service.startup;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.safetyNet.alerts.dto.JsonListDTO;
import com.safetyNet.alerts.reader.IJsonReader;
import com.safetyNet.alerts.reader.ReadJsonData;
import com.safetyNet.alerts.repository.FireStationRepository;
import com.safetyNet.alerts.repository.MedicalRecordsRepository;
import com.safetyNet.alerts.repository.PersonRepository;

@Service
public class DataService implements CommandLineRunner {

	private static Logger logger = LogManager.getLogger(ReadJsonData.class);

	@Autowired
	@Qualifier("personRepoSingleton")
	private PersonRepository personRepository;

	@Autowired
	@Qualifier("fireStationRepoSingleton")
	private FireStationRepository fireStationRepository;

	@Autowired
	@Qualifier("medicalRecordsRepoSingleton")
	private MedicalRecordsRepository medicalRecordsRepository;

	@Override
	public void run(String... args) {
		try {

			IJsonReader readJsonData = new ReadJsonData();
			JsonListDTO dto = readJsonData.getObjects();
			personRepository.addAll(dto.getListPersons());
			medicalRecordsRepository.addAll(dto.getListMedicalRecords());
			fireStationRepository.addAll(dto.getListFireStations());

		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
