package com.safetyNet.alerts.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
		assertEquals(fireStation, list.get(list.size() - 1));
		assertEquals(list.size(), oldSize + 1);

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
		assertEquals(list.size(), oldSize);
		assertFalse(list.contains(fireStation));
	}
}
