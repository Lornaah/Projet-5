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

import com.safetyNet.alerts.model.Person;

@SpringBootTest
public class PersonRepositoryTest {

	@Autowired
	@Qualifier("personRepoSingleton")
	private PersonRepository personRepository;

	@BeforeEach
	private void beforeEach() {
		personRepository.clear();
	}

	@Test
	public void personAddTest() {

		// Arrange
		Person person = new Person();
		int oldSize = personRepository.findAll().size();

		// Act
		personRepository.add(person);

		// Assert
		List<Person> list = personRepository.findAll();
		assertEquals(person, list.get(list.size() - 1)); // Check the last element of the list
		assertEquals(list.size(), oldSize + 1); // Check the size of the list
	}

	@Test
	public void personDeleteTest() {
		// Arrange

		Person person = new Person();
		int oldSize = personRepository.findAll().size();
		personRepository.add(person);

		// Act

		personRepository.delete(person);

		// Assert

		List<Person> list = personRepository.findAll();
		assertEquals(list.size(), oldSize); // Compare lists size
		assertFalse(list.contains(person)); // Check that the element is missing
	}

	@Test
	public void personUpdateTest() {
		// Arrange

		Person personAdd = new Person();
		personRepository.add(personAdd);
		personAdd.setFirstName("firstNameTest");
		personAdd.setLastName("lastNameTest");

		Person personUpdate = new Person();
		personUpdate.setFirstName("firstNameTest");
		personUpdate.setLastName("lastNameTest");
		personUpdate.setAddress("addressTest");

		// Act

		personRepository.update(personUpdate);

		// Assert

		List<Person> list = personRepository.findAll();
		assertTrue(list.contains(personAdd)); // Check that the element is present
		assertTrue(personAdd.getAddress().equals("addressTest")); // Check that address has changed
	}
}
