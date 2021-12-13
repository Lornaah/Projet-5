package com.safetyNet.alerts.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
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
		assertEquals(medicalRecords, list.get(list.size() - 1)); // Check the last element of the list
		assertEquals(list.size(), oldSize + 1); // Check the size of the list

	}

	@Test
	public void medicalRecordsDeleteTest() {
		// Arrange

		MedicalRecords medicalRecords = new MedicalRecords();
		int oldSize = medicalRecordsRepository.findAll().size();
		medicalRecordsRepository.add(medicalRecords);

		// Act
		medicalRecordsRepository.delete(medicalRecords);

		// Assert
		List<MedicalRecords> list = medicalRecordsRepository.findAll();
		assertEquals(list.size(), oldSize); // Compare lists size
		assertFalse(list.contains(medicalRecords)); // Check that the element is missing
	}

	@Test
	public void medicalRecordsUpdateTest() {
		// Arrange
		MedicalRecords medicalRecordsAdd = new MedicalRecords();
		medicalRecordsRepository.add(medicalRecordsAdd);
		medicalRecordsAdd.setFirstName("firstNameTest");
		medicalRecordsAdd.setLastName("lastNameTest");

		MedicalRecords medicalRecordsUpdate = new MedicalRecords();
		medicalRecordsRepository.add(medicalRecordsUpdate);
		medicalRecordsUpdate.setFirstName("firstNameTest");
		medicalRecordsUpdate.setLastName("lastNameTest");

		List<String> allergies = new ArrayList<>();
		allergies.add("pollen");
		medicalRecordsUpdate.setAllergies(allergies);

		// Act

		medicalRecordsRepository.update(medicalRecordsUpdate);

		// Assert

		List<MedicalRecords> list = medicalRecordsRepository.findAll();
		assertTrue(list.contains(medicalRecordsAdd)); // Check that the element is present
		assertTrue(allergies.equals(medicalRecordsAdd.getAllergies())); // Check that allergies have changed
	}
}
