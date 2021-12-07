package com.safetyNet.alerts.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.alerts.model.FireStation;
import com.safetyNet.alerts.service.FireStationService;

@RestController
@RequestMapping("/firestations")
public class FireStationController {

	@Autowired
	private FireStationService firestationService;

	@GetMapping
	public List<FireStation> findAll() {
		return firestationService.findAll();
	}
}
