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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.alerts.model.FireStation;
import com.safetyNet.alerts.service.FireStationService;

@RestController
public class FireStationController {

	private static Logger logger = LogManager.getLogger(PersonController.class);

	@Autowired
	private FireStationService fireStationService;

	@GetMapping("/firestations")
	public List<FireStation> findAll() {
		logger.info("getFireStations called");
		List<FireStation> fireStation = fireStationService.findAll();
		logger.info("getFireStations response : " + fireStation);
		return fireStation;
	}

	@PostMapping("/firestations")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@RequestBody FireStation fireStation) {
		logger.info("addFireStation called");
		fireStationService.add(fireStation);
		logger.info("fireStation added " + fireStation.toString());
	}

	@DeleteMapping("/firestations")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@RequestBody FireStation fireStation) {
		logger.info("deleteFireStation called");
		fireStationService.delete(fireStation);
		logger.info("fireStation deleted " + fireStation.toString());
	}

	@PutMapping("/firestations")
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@RequestBody FireStation fireStation) {
		logger.info("updateFireStation called");
		fireStationService.update(fireStation);
		logger.info("fireStation updated " + fireStation.toString());
	}

}
