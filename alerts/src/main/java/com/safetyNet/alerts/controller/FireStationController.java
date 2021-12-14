package com.safetyNet.alerts.controller;

import java.util.List;

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

	@Autowired
	private FireStationService fireStationService;

	@GetMapping("/firestations")
	public List<FireStation> findAll() {
		return fireStationService.findAll();
	}

	@PostMapping("/firestations")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@RequestBody FireStation fireStation) {
		fireStationService.add(fireStation);
	}

	@DeleteMapping("/firestations")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@RequestBody FireStation fireStation) {
		fireStationService.delete(fireStation);
	}

	@PutMapping("/firestations")
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@RequestBody FireStation fireStation) {
		fireStationService.update(fireStation);
	}

}
