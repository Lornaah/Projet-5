package com.safetyNet.alerts.service;

import java.util.List;

import com.safetyNet.alerts.model.Person;

public interface PersonService {
	
	public void add(Person element);

	public void delete(Person element);

	public void update(Person element);

	public List<Person> findAll();
}
