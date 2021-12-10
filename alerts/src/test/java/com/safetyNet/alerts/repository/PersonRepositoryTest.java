package com.safetyNet.alerts.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
		System.out.println("Person : " + oldSize);
		personRepository.add(person);

		// Act

		personRepository.delete(person);

		// Assert

		List<Person> list = personRepository.findAll();
		assertEquals(list.size(), oldSize);
		assertFalse(list.contains(person));
	}
}
