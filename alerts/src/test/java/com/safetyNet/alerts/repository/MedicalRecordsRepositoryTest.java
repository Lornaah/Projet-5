package com.safetyNet.alerts.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetyNet.alerts.model.MedicalRecords;

@SpringBootTest
public class MedicalRecordsRepositoryTest {

	@Autowired
	@Qualifier("medicalRecordsRepoSingleton")
	private MedicalRecordsRepository medicalRecordsRepository;

	@BeforeEach
	private void beforeEach() {
		medicalRecordsRepository.clear();
	}

	@Test
	public void medicalRecordsAddTest() {
		// Arrange

		MedicalRecords medicalRecords = new MedicalRecords();
		int oldSize = medicalRecordsRepository.findAll().size();

		// Act

		medicalRecordsRepository.add(medicalRecords);

		// Assert

		List<MedicalRecords> list = medicalRecordsRepository.findAll();
		assertEquals(medicalRecords, list.get(list.size() - 1));
		assertEquals(list.size(), oldSize + 1);

	}

	@Test
	public void medicalRecordsDeleteTest() {
		// Arrange

		MedicalRecords medicalRecords = new MedicalRecords();
		int oldSize = medicalRecordsRepository.findAll().size();
		System.out.println("Medical Records : " + oldSize);
		medicalRecordsRepository.add(medicalRecords);

		// Act
		medicalRecordsRepository.delete(medicalRecords);

		// Assert
		List<MedicalRecords> list = medicalRecordsRepository.findAll();
		assertEquals(list.size(), oldSize);
		assertFalse(list.contains(medicalRecords));
	}
}
