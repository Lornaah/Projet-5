package com.safetyNet.alerts.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetyNet.alerts.model.Person;
import com.safetyNet.alerts.serializer.PersonSerializer;
import com.safetyNet.alerts.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Override
	public List<Person> findAll() {
		List<Person> liste = new ArrayList<>();
		PersonSerializer personSerializer = new PersonSerializer();
		try {
			personSerializer.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}

		liste.addAll(personSerializer.getList());
		return liste;
	}

}
