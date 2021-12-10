package com.safetyNet.alerts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.safetyNet.alerts.model.Person;
import com.safetyNet.alerts.repository.PersonRepository;
import com.safetyNet.alerts.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	@Qualifier("personRepoSingleton")
	PersonRepository personRepository;

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public void add(Person element) {
		personRepository.add(element);
	}

	@Override
	public void delete(Person element) {
		personRepository.delete(element);
	}

	@Override
	public void update(Person element) {
		personRepository.update(element);
	}

}
