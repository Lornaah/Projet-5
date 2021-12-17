package com.safetyNet.alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.safetyNet.alerts.dto.request.FireAlertDTO;
import com.safetyNet.alerts.dto.request.PersonInfoDTO;
import com.safetyNet.alerts.dto.request.fireStationInfo.FireStationInfosDTO;
import com.safetyNet.alerts.dto.request.floodAlert.FloodAlertDTO;
import com.safetyNet.alerts.model.Person;
import com.safetyNet.alerts.service.PersonService;

@RestController
public class PersonController {

	private static Logger logger = LogManager.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	@GetMapping("/persons")
	public List<Person> findAll() {
		logger.info("getPersons called");
		List<Person> persons = personService.findAll();
		logger.info("getPersons response : " + persons);
		return persons;
	}

	@PostMapping("/persons")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@RequestBody Person person) {
		logger.info("addPerson called");
		personService.add(person);
		logger.info("person added " + person.toString());
	}

	@PutMapping("/persons")
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@RequestBody Person person) {
		logger.info("putPerson called");
		personService.update(person);
		logger.info("person updated " + person.toString());
	}

	@DeleteMapping("/persons")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@RequestBody Person person) {
		logger.info("deletePerson called");
		personService.delete(person);
		logger.info("person deleted " + person.toString());
	}

	@GetMapping("/communityEmail")
	@ResponseStatus(code = HttpStatus.OK)
	public List<String> getCommunityMail(@RequestParam(name = "city") String city) {
		logger.info("getCommunityMail called");
		List<String> mails = personService.getCommunityMail(city);
		logger.info("getCommunityMail response : " + mails);
		return mails;

	}

	@GetMapping("/phoneAlert")
	@ResponseStatus(code = HttpStatus.OK)
	public List<String> getPhoneByFireStation(@RequestParam(name = "firestation") String firestationNum) {
		logger.info("phoneAlert called");
		List<String> persons = personService.getPhoneByFireStation(firestationNum);
		logger.info("phoneAlert response : " + persons);
		return persons;
	}

	@GetMapping("/personInfo")
	@ResponseStatus(code = HttpStatus.OK)
	public List<PersonInfoDTO> getMedicalRecordsByPerson(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {
		logger.info("personInfo called");
		List<PersonInfoDTO> medicalRecordsByPerson = personService.getMedicalRecordsByPerson(firstName, lastName);
		logger.info("personInfo response : " + medicalRecordsByPerson);
		return medicalRecordsByPerson;

	}

	@GetMapping("childAlert")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ChildAlertDTO> getChildInfos(@RequestParam(name = "address") String address) {
		logger.info("childAlert called");
		List<ChildAlertDTO> childList = personService.getChildInfos(address);
		logger.info("childAlert response : " + childList);
		return childList;
	}

	@GetMapping("firestation")
	@ResponseStatus(code = HttpStatus.OK)
	public FireStationInfosDTO getPersonByStationNumber(@RequestParam(name = "stationNumber") String stationNumber) {
		logger.info("personByStationNumber called");
		FireStationInfosDTO persons = personService.getPersonByStationNumber(stationNumber);
		logger.info("personByStationNumber response : " + persons);
		return persons;
	}

	@GetMapping("flood/stations")
	@ResponseStatus(code = HttpStatus.OK)
	public List<FloodAlertDTO> getFloodAlert(@RequestParam(name = "stations") List<String> stations) {
		logger.info("floodAlert called");
		List<FloodAlertDTO> floodAlertList = personService.getFloodAlert(stations);
		logger.info("floodAlert response " + floodAlertList);
		return floodAlertList;
	}

	@GetMapping("fire")
	@ResponseStatus(code = HttpStatus.OK)
	public FireAlertDTO getFireAlert(@RequestParam(name = "address") String address) {
		logger.info("fireAlert called");
		FireAlertDTO persons = personService.getFireAlert(address);
		logger.info("fireAlert response : " + persons);
		return persons;
	}

}