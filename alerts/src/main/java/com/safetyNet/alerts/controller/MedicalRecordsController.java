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

import com.safetyNet.alerts.model.MedicalRecords;
import com.safetyNet.alerts.service.MedicalRecordsService;

@RestController
public class MedicalRecordsController {

	private static Logger logger = LogManager.getLogger(PersonController.class);

	@Autowired
	private MedicalRecordsService medicalRecordsService;

	@GetMapping("/medicalrecords")
	public List<MedicalRecords> findAll() {
		logger.info("getMedicalRecords called");
		List<MedicalRecords> medicalRecords = medicalRecordsService.findAll();
		logger.info("getMedicalRecords response : " + medicalRecords);
		return medicalRecords;
	}

	@PostMapping("/medicalrecords")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@RequestBody MedicalRecords medicalRecords) {
		logger.info("addMedicalRecords called");
		medicalRecordsService.add(medicalRecords);
		logger.info("medicalRecords added " + medicalRecords.toString());

	}

	@DeleteMapping("/medicalrecords")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@RequestBody MedicalRecords medicalRecords) {
		logger.info("deleteMedicalRecords called");
		medicalRecordsService.delete(medicalRecords);
		logger.info("medicalRecords deleted " + medicalRecords.toString());

	}

	@PutMapping("/medicalrecords")
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@RequestBody MedicalRecords medicalRecords) {
		logger.info("updateMedicalRecords called");
		medicalRecordsService.update(medicalRecords);
		logger.info("medicalRecords updated " + medicalRecords.toString());
	}

}
