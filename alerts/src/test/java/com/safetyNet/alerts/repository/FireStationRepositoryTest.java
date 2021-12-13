package com.safetyNet.alerts.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetyNet.alerts.model.FireStation;

@SpringBootTest
public class FireStationRepositoryTest {

	@Autowired
	@Qualifier("fireStationRepoSingleton")
	private FireStationRepository fireStationRepository;

	@BeforeEach
	private void beforeEach() {
		fireStationRepository.clear();
	}

	@Test
	public void fireStationsAddTest() {
		// Arrange

		FireStation fireStation = new FireStation();
		int oldSize = fireStationRepository.findAll().size();

		// Act

		fireStationRepository.add(fireStation);

		// Assert

		List<FireStation> list = fireStationRepository.findAll();
		assertEquals(fireStation, list.get(list.size() - 1)); // Check the last element of the list
		assertEquals(list.size(), oldSize + 1); // Check the size of the list

	}

	@Test
	public void fireStationDeleteTest() {
		// Arrange

		FireStation fireStation = new FireStation();
		int oldSize = fireStationRepository.findAll().size();
		fireStationRepository.add(fireStation);

		// Act
		fireStationRepository.delete(fireStation);

		// Assert
		List<FireStation> list = fireStationRepository.findAll();
		assertEquals(list.size(), oldSize); // Compare lists size
		assertFalse(list.contains(fireStation)); // Check that the element is missing
	}

	@Test
	public void fireStationUpdateTest() {
		// Arrange
		FireStation fireStationAdd = new FireStation();
		fireStationRepository.add(fireStationAdd);
		fireStationAdd.setAddress("addressTest");

		FireStation fireStationUpdate = new FireStation();
		fireStationRepository.add(fireStationUpdate);
		fireStationUpdate.setAddress("addressTest");
		fireStationUpdate.setStation("numTest");

		// Act

		fireStationRepository.update(fireStationUpdate);

		// Assert

		List<FireStation> list = fireStationRepository.findAll();
		assertTrue(list.contains(fireStationAdd)); // Check that the element is present
		assertTrue(fireStationAdd.getStation().equals("numTest")); // Check that station has changed

	}
}
