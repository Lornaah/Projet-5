package com.safetyNet.alerts.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.alerts.model.Person;
import com.safetyNet.alerts.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping
	public List<Person> findAll() {
		return personService.findAll();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@RequestBody Person person) {
		personService.add(person);
	}

	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@RequestBody Person person) {
		personService.update(person);
	}

	@DeleteMapping
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@RequestBody Person person) {
		personService.delete(person);
	}
}