package com.safetyNet.alerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.alerts.dto.request.ChildAlertDTO;
import com.safetyNet.alerts.dto.request.PersonInfoDTO;
import com.safetyNet.alerts.model.Person;
import com.safetyNet.alerts.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/persons")
	public List<Person> findAll() {
		return personService.findAll();
	}

	@PostMapping("/persons")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@RequestBody Person person) {
		personService.add(person);
	}

	@PutMapping("/persons")
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@RequestBody Person person) {
		personService.update(person);
	}

	@DeleteMapping("/persons")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@RequestBody Person person) {
		personService.delete(person);
	}

	@GetMapping("/communityEmail")
	@ResponseStatus(code = HttpStatus.OK)
	public List<String> getCommunityMail(@RequestParam(name = "city") String city) {
		return personService.getCommunityMail(city);

	}

	@GetMapping("/phoneAlert")
	@ResponseStatus(code = HttpStatus.OK)
	public List<String> getPhoneByFireStation(@RequestParam(name = "firestation") String firestationNum) {
		return personService.getPhoneByFireStation(firestationNum);
	}

	@GetMapping("/personInfo")
	@ResponseStatus(code = HttpStatus.OK)
	public List<PersonInfoDTO> getMedicalRecordsByPerson(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {
		return personService.getMedicalRecordsByPerson(firstName, lastName);

	}

	@GetMapping("childAlert")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ChildAlertDTO> getChildInfos(@RequestParam(name = "address") String address) {
		return personService.getChildInfos(address);
	}

}