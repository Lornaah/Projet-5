package com.safetyNet.alerts.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.safetyNet.alerts.model.Person;
import com.safetyNet.alerts.repository.PersonRepository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

	private final List<Person> list = new ArrayList<>();

	@Bean
	@Scope("singleton")
	public PersonRepositoryImpl personRepoSingleton() {
		return new PersonRepositoryImpl();
	}

	@Override
	public void add(Person element) {
		list.add(element);
	}

	@Override
	public void delete(Person element) {
		list.remove(element);
	}

	@Override
	public void update(Person person) {
		list.forEach(p -> {
			if (p.equals(person)) {
				p.setAddress(person.getAddress());
				p.setCity(person.getCity());
				p.setEmail(person.getEmail());
				p.setPhone(person.getPhone());
			}
		});
	}

	@Override
	public List<Person> findAll() {
		return Collections.unmodifiableList(list);
	}

	@Override
	public void addAll(List<Person> elements) {
		list.addAll(elements);
	}
}
